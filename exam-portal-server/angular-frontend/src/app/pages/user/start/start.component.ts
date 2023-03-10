import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/service/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  qId: any;
  questions: any;

  marksGot = 0;
  correctAnswers = 0;
  attempted = 0;
  isSubmit = false;
  timer: any;

  constructor(private locationSt: LocationStrategy, private _route: ActivatedRoute, private _question: QuestionService) { }
  ngOnInit(): void {
    this.preventBackButton();
    this.qId = this._route.snapshot.params['qId'];
    this.loadQuestions();
  }
  loadQuestions() {
    this._question.getQuestionsOfQuizForTest(this.qId).subscribe(
      (data: any) => {
        this.questions = data;
        this.timer = this.questions.length * 2 * 60;
        this.questions.array.forEach((q: any) => {
          q['givenAnswer'] = '';
        });

        console.log(this.questions);
        this.startTimer();

      },
      (error: any) => {
        console.log(error);
        Swal.fire('Error', 'Error in loading quiz', 'error');
      });
  }
  preventBackButton() {
    history.pushState(null, 'null', location.href);
    this.locationSt.onPopState(() => {
      history.pushState(null, 'null', location.href);
    });
  }
  submitQuiz() {

    Swal.fire({
      title: `Do you want to submit he quiz ?`,
      showCancelButton: true,
      confirmButtonText: `Submit`,
      icon: `info`,
    }).then((e) => {
      if (e.isConfirmed) {
        this.evalQuiz();
      }
    });
  }
  startTimer() {
    let t: any = window.setInterval(() => {
      //code
      if (this.timer <= 0) {
        this.evalQuiz();
        clearInterval(t);
      } else {
        this.timer--;
      }
    }, 1000)
  } getFormattedTime() {
    let mm = Math.floor(this.timer / 60);
    let ss = this.timer - mm * 60;
    return `${mm} min :  ${ss} sec`;
  }
  evalQuiz() {
    //calculation
    this.isSubmit = true;
    this.questions.forEach((q: any) => {

      if (q.givenAnswer == q.answer) {
        this.correctAnswers++
        let marksSingle = this.questions[0].quiz.maxMarks / this.questions.length;
        this.marksGot += marksSingle;
      }
      if (q.givenAnswer.trim() != '') {
        this.attempted++;
      }
    });
  }
}