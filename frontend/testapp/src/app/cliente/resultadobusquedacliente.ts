import { Cliente } from './cliente';

export class ResultadoBusquedaCliente {
	clientes: Cliente[];
	pagina: number;
	resultadosPorPagina: number;
	cantidadPaginas: number;
}
