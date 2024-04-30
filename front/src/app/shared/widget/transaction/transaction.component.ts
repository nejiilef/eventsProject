import { Component, OnInit, Input } from '@angular/core';

import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit {

  modalRef?: BsModalRef;

  @Input() transactions: Array<{
    id?: number;
    heure_debut?: Date,
    heure_fin?: Date,
    jour?: Date
  }>;

  constructor(private modalService: BsModalService) { }

  ngOnInit() {
  }

  /**
   * Open modal
   * @param content modal content
   */
  openModal(content: any) {
    this.modalRef = this.modalService.show(content);
  }

}
