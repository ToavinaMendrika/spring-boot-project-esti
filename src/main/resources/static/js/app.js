$(document).ready(function() {
    $('#datatable').DataTable();
    $('.select2').select2();

    $('.row td.products').each(function(){
        var product = $(this).data('products');
        var txt = '';
        product.products.forEach(function(element, index){
            if(index < 4 && element.product != undefined){
                txt += index == 0 ? element.product : ', ' +element.product;
            }
        });
        $(this).text(txt);
    });
    if($('#product-show-page').length != 0){
        var productShow = $('#products-show').data('products');
        if(productShow != undefined){

            $('#sub-total').text(productShow.stotal);
            $('#tax').text(productShow.tax + '%');
            $('#tax-amount').text(productShow.taxtAmount);
            $('#g-total').text(productShow.gtotal);
        }
        $('#tab_logic tbody').html('');
        productShow.products.forEach(function(value, index){
            //ajout tableau
            if(value.product != undefined){
                var $td = $(' <td>'+(index+1)+'</td>\n' +
                    '<td>'+value.product+'</td>\n' +
                    '<td>'+value.qty+'</td>\n' +
                    '<td>'+value.price+'</td>\n' +
                    '<td>'+value.total+'</td>');
                $('#tab_logic tbody').append('<tr id="addr'+(index+1)+'"></tr>').append($td);
            }
        });
    }
    var i=1;
    $("#add_row").click(function(e){
        b=i-1;
        e.preventDefault();
        $('#addr'+i).html($('#addr'+b).html()).find('td:first-child').html(i+1);
        $('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
        i++;
    });
    $("#delete_row").click(function(e){
        e.preventDefault();
        if(i>1){
            $("#addr"+(i-1)).html('');
            i--;
        }
        calc();
    });

    $('#tab_logic tbody').on('keyup change',function(){
        calc();
    });
    $('#tax').on('keyup change',function(){
        calc_total();
    });


});

function calc()
{
    $('#tab_logic tbody tr').each(function(i, element) {
        var html = $(this).html();
        if(html!='')
        {
            var qty = $(this).find('.qty').val();
            var price = $(this).find('.price').val();
            $(this).find('.total').val(qty*price);

            calc_total();
        }
    });
}

function rowToJson()
{
    var JsonResult = {
        gtotal: 0,
        tax: 0,
        taxtAmount: 0,
        stotal: 0,
        products: []
    };
    $('#tab_logic tbody tr').each(function (element) {

        var product = $(this).find('.product').val();
        var qty = $(this).find('.qty').val();
        var price = $(this).find('.price').val();
        var total = $(this).find('.total').val();
        JsonResult.gtotal = $('#total_amount').val();
        JsonResult.tax = $('#tax').val();
        JsonResult.taxtAmount = $('#tax_amount').val();
        JsonResult.stotal = $('#sub_total').val();
        JsonResult.products.push({
            product, qty, price, total
        });
    })
    return JsonResult;
}

function calc_total()
{
    var jsonProducts = JSON.stringify(rowToJson());
    $('#products-json').val(jsonProducts);
    total=0;
    $('.total').each(function() {
        total += parseInt($(this).val());
    });
    $('#sub_total').val(total.toFixed(2));
    tax_sum=total/100*$('#tax').val();
    $('#tax_amount').val(tax_sum.toFixed(2));
    $('#total_amount').val((tax_sum+total).toFixed(2));
}

var invoiceMounth = $('#invoice-mounth').data('invoice-mounth');
var countMounth = 0;
invoiceMounth.forEach(function(invoice){
    countMounth += parseFloat(invoice.gtotal);
});
$('#invoice-mounth').text(countMounth);

function printDiv(divName) {
    var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
}