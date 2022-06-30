var categoryObject = {
    "Solid": ["Medicines", "Fruits and Vegetables", "Bakery Items", "Meat", "Cereals"],
    "Liquid" : ["Drinks", "Milk Products", "Medicinal Syrups"],
    "Semi Solid": ["Milk Products", "Bakery Items"]
}
  window.onload = function() {
    var catSel = document.getElementById("cat");
    var subCatSel = document.getElementById("subCat");
    for (var x in categoryObject) {
      catSel.options[catSel.options.length] = new Option(x, x);
    }
    catSel.onchange = function() {
   //empty Chapters- and Topics- dropdowns
  
   subCatSel.length = 1;
      //display correct values
      var y =  categoryObject[this.value];
      for (var i = 0; i < y.length; i++) {
        subCatSel.options[subCatSel.options.length] = new Option(y[i], y[i]);
    }
  }
}