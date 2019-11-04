import { Component, OnInit, Output } from '@angular/core';
import { TipoCobertura } from 'TipoCobertura';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EventEmitter } from '@angular/core';

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

  coberturaSeleccionada: String;
  @Output() emitterBoolean = new EventEmitter<boolean>();

  constructor(private modalService: NgbModal) { }

  openVerticallyCentered(content) {
    this.modalService.open(content, { centered: true });
  }
  seleccionarCobertura(cobertura){
    this.coberturaSeleccionada = cobertura;
  }
  onSubmit(){
    this.coberturas.forEach(element => {
      if (element.nombre == this.coberturaSeleccionada){
        console.log(element);
      }
    });
  }
  ngOnInit() {
  }
  return(volver){
    this.emitterBoolean.emit(volver);
  }
}
