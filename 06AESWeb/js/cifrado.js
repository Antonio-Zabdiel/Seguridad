//variables

var cadena = "Habia una vez un patito que decia miau miau miau y tenia hambre de chocolate";
var password = "qwertyui";  //le esta agregando el padding

//proceso

var cifrar = CryptoJS.AES.encrypt(cadena, password);
var descifrar = CryptoJS.AES.decrypt(cifrar, password);

//obtener los datos

document.getElementById("demo0").innerHTML = cadena;
document.getElementById("demo1").innerHTML = cifrar;
document.getElementById("demo2").innerHTML = descifrar;//no tiene formato :()
document.getElementById("demo3").innerHTML = descifrar.toString(CryptoJS.enc.Utf8);//ahora si con formasto
