var System = require('es6-module-loader').System;

System.import('./app').then(function(index){
    index.run(__dirname);    
}).catch(function(err){
    console.log(err);    
});
