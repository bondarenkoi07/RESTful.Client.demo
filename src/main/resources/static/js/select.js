let tableHeadElement = document.getElementById('parent');
let columns = tableHeadElement.children;
let field = document.getElementById('field');
[].forEach.call(columns,function(i) {
        i.addEventListener('mousedown', function (e){
            field.value= i.id
    })
})