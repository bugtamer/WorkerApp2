// una vez actualizado el DOM
$('.itemResultado').each(function (i){
    this.addEventListener('click', function(){
        location = $(this).find('a').attr('href');
    });
});