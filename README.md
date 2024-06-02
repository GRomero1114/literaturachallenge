<h1>Literatura Challenge</h1>
 Desarrollada con JPA, Hibernate y PostgreSQL en lenguaje JAVA. Usando la API de código abierto <b>Gutendex</b>(<a>https://gutendex.com/</a>) permite buscar libros por diferentes parámetros y los guarda en un registro a los cuales se puede acceder también mediante la aplicación.<br>
Se compone de un Menú principal donde se puede elegir entre 2 opciones las cuales despliegan otro menú cada una:<br>
<ul>
<li><b>Buscar libros para agregar:</b> Un menú donde se puede buscar un libro para agregar a la base de datos con PostgreSQL.<br>
                    - Buscar libro por título: Busca libros por título o parte del mismo. Adicionalmente, también se puede buscar un libro por el nombre del autor.<br>
                  <b>*I</b>- Buscar libros por Idioma: Busca libros por el idioma en el que fue escrito, los idiomas principales se muestran en pantalla para dar un ejemplo. Adicionalmente, si se escribe la palabra help, se muestran todos los idiomas disponibles junto a su código, en esta opción se debe escribir el código para que sea aceptada como una opción válida.<br>
                    - Buscar libros por Fecha: Busca libros por un rango determinado de años.<br>
<li><b> Mostrar libros registrados:</b> Un menú que muestra los libros registrados en la base de datos, los cuales se pueden filtrar por mediante parámetros.<br>
                    - Mostrar lista de libros: Ofrece el registro de todos los libros agregados. Los registros presentan el título, autores, idioma y el total de descargas.<br>
                    - Mostrar lista de Autores Ofrece el registro de todos los autores agregados. Los registros presentan el nombre, fecha de nacimiento, fecha de deceso y los libros registrados que escribió.<br>
                    - Mostrar autores registrados vivos en un determinado año: Ingresando el año, se muestran los autores vivos en ese año.<br>
                    - Mostrar libros por idioma:  Funciona igual que *I, pero mostrando los libros en los registros en lugar de agregar uno nuevo.<br>
                    - Mostrar Top 10 libros: Presenta los 10 mejores libros registrados.<br>
                    - Buscar algún autor registrado por Nombre: Muestra el autor que se ingrese para buscar.</ul>
<br>
<h2>Contemplaciones a tener en cuenta:</h2>
<p>-Las búsquedas se hacen a través de la API <b>Gutendex</b>(<a>https://gutendex.com/</a>).<br>
-Las búsquedas no discriminan mayúscula de minúscula.<br>
-En el caso de que la búsqueda no sea lo suficientemente especifica, se mostraran solo los primeros 32 libros que coincidan con la búsqueda, ordena de mayor a menor por número de descargas. Donde {1} es el resultado con más descargas.<br>
-En los menús solo se pueden ingresar números.<br></p>
