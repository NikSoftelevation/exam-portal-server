import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/service/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quiz-questions',
  templateUrl: './view-quiz-questions.component.html',
  styleUrls: ['./view-quiz-questions.component.css'],
})
export class ViewQuizQuestionsComponent implements OnInit {
  qid: any;
  qTitle: any;
  questions = [
    {
      quesId:'',
      content: '',
      option1: '',
      option2: '',
      option3: '',
      option4: '',
      answer: '',
    }


  ];
  constructor(
    private _route: ActivatedRoute,
    private _question: QuestionService,
    private _snack: MatSnackBar
  ) { }
  ngOnInit(): void {
    this.qid = this._route.snapshot.params['qId'];
    this.qTitle = this._route.snapshot.params['title'];
    this._question.getQuestionsOfQuiz(this.qid).subscribe(
      (data: any) => {
        console.log(data);
        this._question = data;

      },
      (error) => {
        console.log(error);
      }
    );
    console.log(this.qid);
    console.log(this.qTitle);
  }
  //delete question
  deleteQuestion(qid: any) {
    Swal.fire({

      icon: 'info',
      showCancelButton: true,
      confirmButtonText: 'Delete',
      title: 'Are you sure ! you to want to delete this questuion ?'
    }).then((result) => {
      if (result.isConfirmed) {
        this._question.deleteQuestion(qid).subscribe((data: any) => {
          this._snack.open('Question Deleted !', '', {
            duration: 3000,
          });
          this.questions = this.questions.filter((q) => q.quesId != qid);
        },
          (error: any) => {
            this._snack.open('Error in deleteing questions', '', {
              duration: 3000,
            });
            console.log(error);
          }
        );
      }
    });
  }
}