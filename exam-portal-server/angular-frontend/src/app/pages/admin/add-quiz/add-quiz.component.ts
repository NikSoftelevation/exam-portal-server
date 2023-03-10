import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../../service/category.service";
import Swal from "sweetalert2";
import {MatSnackBar} from "@angular/material/snack-bar";
import {QuizService} from "../../../service/quiz.service";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css']
})
export class AddQuizComponent implements OnInit {
  categories  :any = [];

  quizData = {
    title: '',
    content: '',
    maxMarks: '',
    numberOfQuestions: '',
    active: 'true',
    category:
      {
        categoryId: '',
      },
  };

  constructor(private cat: CategoryService, private snack: MatSnackBar, private quiz: QuizService) {
  }

  ngOnInit() {
    this.cat.categories().subscribe(
      (data: any) => {
        this.categories = data
        console.log(this.categories);
      },
      (error: any) => {
        console.log(error)
        Swal.fire('Error !', 'Internal Server Error', 'error');
      }
    );
  }

  addQuiz() {
    if (this.quizData.title.trim() == '' || this.quizData.title == null) {
      this.snack.open('Title required !', '', {
        duration: 3000,
      });
      return;
    }
    this.quiz.addQuiz(this.quizData).subscribe(
      (data) => {
        Swal.fire('Success', 'Quiz added successfully', 'success');
        this.quizData = {
          title: '',
          content: '',
          maxMarks: '',
          numberOfQuestions: '',
          active: 'true',
          category:
            {
              categoryId: '',
            },
        };
      },
      (error) => {
        Swal.fire('Error !', 'Error while adding quiz', 'error')
        console.log(error);
      });
  }
}
