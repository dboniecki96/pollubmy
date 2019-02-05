import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrivatelessonsComponent } from './privatelessons.component';

describe('PrivatelessonsComponent', () => {
  let component: PrivatelessonsComponent;
  let fixture: ComponentFixture<PrivatelessonsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrivatelessonsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrivatelessonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
