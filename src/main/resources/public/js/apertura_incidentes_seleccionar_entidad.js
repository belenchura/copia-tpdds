document.getElementById('entidad').addEventListener('change', function () {
    const entidadId = this.value;
    const form = document.getElementById('miFormulario');
    form.action = `/incidentes/apertura/${entidadId}`;
});