// Funcionalidad para mensajes flash
document.addEventListener('DOMContentLoaded', function() {
    // Buscar el mensaje flash
    var alert = document.querySelector('.alert-flash');
    
    if (alert) {
        // Cerrar automáticamente después de 4 segundos
        setTimeout(function() {
            alert.style.transition = 'opacity 0.5s';
            alert.style.opacity = '0';
            setTimeout(function() {
                if (alert && alert.parentNode) {
                    alert.remove();
                }
            }, 500);
        }, 4000);
    }
});