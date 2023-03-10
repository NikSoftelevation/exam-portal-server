import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import baseUrl from 'src/app/service/helper';
import { QuizService } from 'src/app/service/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css'],
})
export class ViewQuizzesComponent implements OnInit {
  quizzes = [
    {
      qid: 8,
      title: '',
      content: '',
      maxMarks: '',
      numberOfQuestions: '',
      active: '',
      category: {
        title: '',
      },
    },
    {
      qid: 9,
      title: '',
      content: '',
      maxMarks: '',
      numberOfQuestions: '',
      active: 'true',
      category: {
        title: '',
      },
    },
    {
      qid: 10,
      title: '',
      content: '',
      maxMarks: '',
      numberOfQuestions: '',
      active: 'true',
      category: {
        title: '',
      },
    },
    {
      qid: 12,
      title: '',
      content: '',
      maxMarks: '',
      numberOfQuestions: '',
      active: 'true',
      category: {
        title: '',
      },
    },
    {
      qid: 13,
      title: '',
      content: '',
      maxMarks: '',
      numberOfQuestions: '',
      active: 'true',
      category: {
        title: '',
      },
    },
  ];

  constructor(private quiz: QuizService, private http: HttpClient) { }

  ngOnInit(): void {
    this.quiz.quizzes().subscribe(
      (data: any) => {
        this.quizzes = data;
        console.log(this.quizzes);
      },
      (error) => {
        console.log(error);
        Swal.fire('Error !!', 'Error in loading data', 'error');
      }
    );
  }
  deleteQuiz(qId: any) {
    /* this.quiz.deleteQuiz(qId).subscribe(
       (data: any) => {
         this.quizzes=this.quizzes.filter((quiz) => quiz.qId !=qId);
         Swal.fire('Success !', 'Quiz Deleted!', 'success');
       },
       (error: any) => {
         console.log(error);
         Swal.fire('Error !', 'Error while deleting quiz', 'error');
       }
     ); */
    Swal.fire({
      icon: 'warning',
      title: 'Are you sure you want to delete quiz?',
      confirmButtonText: 'Delete',
      showCancelButton: true,
    });
  }
}
