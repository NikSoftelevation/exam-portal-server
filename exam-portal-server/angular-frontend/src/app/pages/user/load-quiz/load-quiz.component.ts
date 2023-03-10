import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuizService } from 'src/app/service/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrls: ['./load-quiz.component.css']
})
export class LoadQuizComponent implements OnInit {
  categoryId: any;
  quizzes: any;
  constructor(private _route: ActivatedRoute, private _quiz: QuizService) { }
  ngOnInit(): void {
    this._route.params.subscribe((params) => {
      this.categoryId = params['categoryId'];
      if (this.categoryId == 0) {
        console.log('Load all the quizzes');

        this._quiz.getActiveQuizzes().subscribe(
          (data: any) => {
            this.quizzes = data;
            console.log(this.quizzes);
          },
          (error) => {
            Swal.fire('Error', 'Error in loading all the quizzes', 'error');
            console.log(error);
          });
      } else {
        console.log('Load specific quiz');

        this._quiz.getActiveQuizzesOfCategory(this.categoryId).subscribe(

          (data: any) => {
            this.quizzes = data;
          },
          (error: any) => {
            console.log(error);
            alert('Error in loading quiz data');
          });
      }
    });
  }
}