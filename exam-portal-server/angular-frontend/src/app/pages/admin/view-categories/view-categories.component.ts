import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/service/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-categories',
  templateUrl: './view-categories.component.html',
  styleUrls: ['./view-categories.component.css'],
})
export class ViewCategoriesComponent implements OnInit {
  categories = [
    {
      categoryId: 1,
      title: ' ',
      description: '',
    },

    {
      categoryId: 2,
      title: '',
      description: '',
    },
    {
      categoryId: 3,
      title: ' ',
      description: '',
    },
    {
      categoryId: 4,
      title: '',
      description: '',
    },
  ];
  constructor(private category: CategoryService) {}

  ngOnInit(): void {
    this.category.categories().subscribe(
      (data: any) => {
        this.categories = data;
        console.log(this.categories);
      },
      (error) => {
        console.log(error);
        Swal.fire('Error', 'Server Error', 'error');
      }
    );
  }
}
