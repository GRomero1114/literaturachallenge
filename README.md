-Literatura Challenge desarrollada con JPA, Hibernate y PostgreSQL en lenguaje JAVA. Usando la API de codigo abierto Gutendex(https://gutendex.com/) permite buscar libros por diferente parametros y los guarda en un registro a los cuales se puede acceder tambien mediante la aplicacion.
Se compone de un Menú principal donde se puede elegir entre 2 opciones las cuales despliegan otro menú cada una:
{1} Buscar libros para agregar: Un menú donde se puede buscar un libro para agregar a la base de datos con PostgreSQL.
                    - Buscar libro por titulo: Busca libros por titulo o parte del mismo. Adicionalmente tambien se puede buscar un libro por el nombre del autor.
                  *I- Buscar libros por Idioma: Busca libros por el idioma en el que fue escrito, los idiomas principales se muestran en pantalla para dar un ejemplo. Adicionalmente si se escribe la palabra help, se muestran todos los idiomas disponibles junto a su codigo, en esta opcion se debe escribir el codigo para que sea aceptada como una opcion valida.
                    - Buscar libros por Fecha: Busca libros por un rango determinado de años.
{2} Mostrar libros registrados: Un menú que muestra los libros registrados en la base de datos, los cuales se pueden filtrar por mediante parametros.
                    - Mostrar lista de libros: Ofrece el registro de todos los libros agregados. Los registros presentan el titulo, autores, idioma y el total de descargas
                    - Mostrar lista de Autores Ofrece el registro de todos los autores agregados. Los registros presentan el nombre, fecha de nacimiento, fecha de deceso y los libros registrados que escribió.
                    - Mostrar autores registrados vivos en un determinado año: Ingresando el año, se muestran los autores vivos en ese año
                    - Mostrar libros por idioma:  Funciona igual que *I pero mostrando los libros en los registros en lugar de agregar uno nuevo.
                    - Mostrar Top 10 libros: Presenta los 10 mejores libros registrados.
                    - Buscar algun autor registrado por Nombre: Muestra el autor que se ingrese para buscar.

Contemplaciones a tener en cuenta:
-Las busquedas se hacen a travez de la API Gutendex(https://gutendex.com/).
-Las busquedas no discriminan mayuscula de minuscula.
-En el caso de que la busqueda no sea lo suficientemente especifica, se mostraran solo los primeros 32 libros que coincidan con la busqueda ordena de mayor a menor por numero de descargas. Donde {1} es el resultado con más descargas.
-En los menús solo se pueden ingresar numeros.
