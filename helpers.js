Handlebars.registerHelper('getRoute', function (list,index) {
    let route = `/${list.slice(0,index).join('/')}`
    return route;
   })

   Handlebars.registerHelper('test', function (param) {
    return "TESTINNNNNNGG0"+param;
   })