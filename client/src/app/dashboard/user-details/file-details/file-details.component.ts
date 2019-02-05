import { FileModel } from './../../file-upload/file.model';
import { FileUploadService } from './../../file-upload/file-upload.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-file-details',
  templateUrl: './file-details.component.html',
  styleUrls: ['./file-details.component.css']
})
export class FileDetailsComponent implements OnInit {

  errorMessage = 'Nie udostępniłeś żadnych materiałów';
  errorStatus: number;
  files: FileModel[];
  constructor(private uploadService: FileUploadService) { }

  ngOnInit() {
    this.uploadService.getUserFiles().subscribe(
      res=>{
        this.files = res;
        console.log(res);
      },err=>{
        console.log(err);
        this.errorStatus = err.status;
      }
    );
  }

  downloadFile(fileInformationId: string){
    this.uploadService.downloadFile(fileInformationId);
  }
  deleteFile(fileInformationId: string){
    if(confirm('Czy na pewno chcesz usunąć ten plik?')){
      if(this.files.length === 1){
        this.files = [];
      }
      this.uploadService.deleteFile(fileInformationId).subscribe(
        res=>{
          this.ngOnInit();
          console.log(res);
        },err=>{
          console.log(err);
          
        },
      );
    }
  }

}
