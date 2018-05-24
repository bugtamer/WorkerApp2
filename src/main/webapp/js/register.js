let validar_Registro = function () {
    let _valid = true;
    let _formValues = {};
    let cadena = "";

    $('form .error').remove();

    $('#name').each(function () {
        _formValues[this.name] = this.value;
        let validez_nombre = this.validity;
        if (validez_nombre.valueMissing) {
            $('#resultado1').html('<p class="error">Introduce un nombre</p>');
             _valid = false;
        } 

    });

    $('#lastname').each(function () {
        _formValues[this.name] = this.value;
        let validez_apellido = this.validity;
        if (validez_apellido.valueMissing) {
            $('#resultado2').html('<p class="error">Introduce un apellido</p>');
            _valid = false;
        }
    });

    $('#email').each(function () {
        _formValues[this.name] = this.value;
        let validez_email = this.validity;
        let expresion = /\w+@\w+\.[a-z]/;//para el correo
        if (validez_email.valueMissing) {
            $('#resultado3').html('<p class="error">Introduce un Email</p>');
            _valid = false;
        }
        if (!validez_email.valid) {
            $('#resultado3').html('<p class="error">Introduce un Email Valido</p>');
            _valid = false;
        }
    });

    $('#pass').each(function () {
        _formValues[this.name] = this.value;
        let validez_pass = this.validity;
        let exp = /^[a-zA-Z]{1,}$/;
        if (validez_pass.valueMissing) {
            $('#resultado4').html('<p class="error">Introduce un password</p>');
            _valid = false;
        }
        if (exp.test(_formValues[this.name])) {
            $('#resultado4').html('<p class="error">Debe contener un número</p>');
            _valid = false;
        }
    });

    $('#repass').each(function () {
        _formValues[this.name] = this.value;
        let exp = /^[0-9]{1,}$/;
        let validez_repass = this.validity;
        if (_formValues.pass != _formValues.repass) {
            $('#resultado5').html('<p class="error">Los passwords no coinciden</p>');
            _valid = false;
        }

    });


    return { valid: _valid, values: _formValues };
}


$('#register').click(function (evnt) {
    evnt.preventDefault();
    let objeto = validar_Registro();
    console.log('objeto a enviar:', objeto);
    if (objeto.valid) {
        $.ajax({
            url: 'http://www.mocky.io/v2/5ae1741c2d000057009d7c06',
            method: 'POST',
            data: objeto
        })
            .done(function (datoRecibido) {
                console.log('datoRecibido:', datoRecibido);
                if (datoRecibido.result) {
                    sessionStorage.setItem('usuarioAutenticado', JSON.stringify(datoRecibido.usuario));
                    location.href= './register_confirm.html'
                } else {
                    $('#pass_error').html('<p class="error">usuario ya existente!</p>')
                }

            });
    }

});

