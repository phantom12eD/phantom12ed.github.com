function mostrarContenido(opcion) {
    var elementos = document.querySelectorAll('div');
    for (var i = 0; i < elementos.length; i++) {
      elementos[i].style.display = 'none';
    }
    document.getElementById(opcion).style.display = 'block';
  }
  