import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CategoryService } from 'src/app/service/category.service';
import { QuizService } from 'src/app/service/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css'],
})
export class UpdateQuizComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private _quiz: QuizService,
    private _cat: CategoryService,
    private _router: Router
  ) {}

  categories: any;

  qid = 0;
  quiz: any;

  ngOnInit(): void {
    this.qid = this.route.snapshot.params['qId'];
    // alert(this.qId);
    this._quiz.getQuiz(this.qid).subscribe(
      (data: any) => {
        this.quiz = data;
        console.log(this.quiz);
      },
      (error: any) => {
        console.log(error);
        Swal.fire('Error !', 'Unable to get quiz', 'error');
      }
    );
    this._cat.categories().subscribe(
      (data: any) => {
        this.categories = data;
      },
      (error: any) => {
        Swal.fire('Error !', 'Internl Server Error', 'error');
      }
    );
  }
  //update form submit
  public updateData() {
    this._quiz.updateQuiz(this.quiz).subscribe(
      (data) => {
        Swal.fire('Success', 'Quiz updated successfully', 'success').then(
          (e) => {
            this._router.navigate(['/admin/quizzes']);
          }
        );
      },
      (error) => {
        Swal.fire('Error', 'Failed to update quiz', 'error');
        console.log(error);
      }
    );
  }
}
