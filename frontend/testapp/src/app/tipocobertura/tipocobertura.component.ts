import { Component, OnInit } from '@angular/core';
import { TipoCobertura } from 'TipoCobertura';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-tipocobertura',
  templateUrl: './tipocobertura.component.html',
  styleUrls: ['./tipocobertura.component.scss']
})
export class TipocoberturaComponent implements OnInit {
  /*coberturas: TipoCobertura[];*/
  coberturas = [
    {
      'nombre': 'total',
      'descripcion': 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vel lectus in sapien aliquet rutrum. Quisque nisl nibh, iaculis vel dictum ut, tincidunt eu turpis. Quisque quis efficitur dui, sed faucibus tellus. Praesent tortor velit, fringilla ut dapibus ac, sodales ut magna. Donec lobortis efficitur massa at lacinia. Integer posuere augue ut quam tincidunt luctus. Etiam eu mollis est. Vestibulum eleifend rutrum nibh.'
    },
    {
      'nombre': 'parcial',
      'descripcion': 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vel lectus in sapien aliquet rutrum. Quisque nisl nibh, iaculis vel dictum ut, tincidunt eu turpis. Quisque quis efficitur dui, sed faucibus tellus. Praesent tortor velit, fringilla ut dapibus ac, sodales ut magna. Donec lobortis efficitur massa at lacinia. Integer posuere augue ut quam tincidunt luctus. Etiam eu mollis est. Vestibulum eleifend rutrum nibh.'
    },
    {
      'nombre': 'casiparcial',
      'descripcion': 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vel lectus in sapien aliquet rutrum. Quisque nisl nibh, iaculis vel dictum ut, tincidunt eu turpis. Quisque quis efficitur dui, sed faucibus tellus. Praesent tortor velit, fringilla ut dapibus ac, sodales ut magna. Donec lobortis efficitur massa at lacinia. Integer posuere augue ut quam tincidunt luctus. Etiam eu mollis est. Vestibulum eleifend rutrum nibh.'
    },
    {
      'nombre': 'casitotal',
      'descripcion': 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vel lectus in sapien aliquet rutrum. Quisque nisl nibh, iaculis vel dictum ut, tincidunt eu turpis. Quisque quis efficitur dui, sed faucibus tellus. Praesent tortor velit, fringilla ut dapibus ac, sodales ut magna. Donec lobortis efficitur massa at lacinia. Integer posuere augue ut quam tincidunt luctus. Etiam eu mollis est. Vestibulum eleifend rutrum nibh.'
    },
    {
      'nombre': 'ndeaa',
      'descripcion': 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vel lectus in sapien aliquet rutrum. Quisque nisl nibh, iaculis vel dictum ut, tincidunt eu turpis. Quisque quis efficitur dui, sed faucibus tellus. Praesent tortor velit, fringilla ut dapibus ac, sodales ut magna. Donec lobortis efficitur massa at lacinia. Integer posuere augue ut quam tincidunt luctus. Etiam eu mollis est. Vestibulum eleifend rutrum nibh.'
    },
    {
      'nombre': 'coscu',
      'descripcion': 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vel lectus in sapien aliquet rutrum. Quisque nisl nibh, iaculis vel dictum ut, tincidunt eu turpis. Quisque quis efficitur dui, sed faucibus tellus. Praesent tortor velit, fringilla ut dapibus ac, sodales ut magna. Donec lobortis efficitur massa at lacinia. Integer posuere augue ut quam tincidunt luctus. Etiam eu mollis est. Vestibulum eleifend rutrum nibh.'
    }
  ];

  constructor(private modalService: NgbModal) { }

  openVerticallyCentered(content) {
    this.modalService.open(content, { centered: true });
  }
  ngOnInit() {
  }

}
