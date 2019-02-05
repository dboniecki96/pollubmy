import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddprivatelessonComponent } from './addprivatelesson.component';

describe('AddprivatelessonComponent', () => {
  let component: AddprivatelessonComponent;
  let fixture: ComponentFixture<AddprivatelessonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddprivatelessonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddprivatelessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
