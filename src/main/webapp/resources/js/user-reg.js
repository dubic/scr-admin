/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var m = 0;
$(function() {
    $('#nextMonthBtn').click(function(){
        m++;
        displayChart(m);
    });
    $('#prevMonthBtn').click(function(){
        m--;
        displayChart(m);
    });
    displayChart();
    function displayChart(month) {
        var url = '/scr-admin/api/charts/registration';
        if (month !== undefined) {
            url = url + "?month=" + month;
        }
        $.getJSON(url, function(cv) {
            var categories = [];
            var values = [];
            for (var i = 0; i < cv.pairs.length; i++) {
                categories.push(cv.pairs[i].key);
                values.push(cv.pairs[i].lvalue);
            }

            $('#reg-chart').highcharts({
                title: {
                    text: 'User Registration - ' + cv.month,
                    x: -20 //center
                },
//                subtitle: {
//                    text: 'Source: WorldClimate.com',
//                    x: -20
//                },
                xAxis: {
                    categories: categories
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'num of users'
                    },
                    plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                },
                tooltip: {
//                        valueSuffix: 'N'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                        name: 'Registrations',
                        data: values
                    }]
            });


        });
    }

///////////////////////////////////////////////////////////////////////////////////////
    var usersTable = $('#usersTable').dataTable({
        "aaSorting": [[3, "desc"]],
        "bProcessing": true,
        "bServerSide": true,
        "aoColumnDefs": [{"bSortable": false, "aTargets": [2]}],
        "sPaginationType": "bootstrap",
//        "sPaginationType": "bootstrap",
        "sAjaxSource": "/scr-admin/api/tables/users"////START HERE TO SERVLET
    });

    jQuery('.dataTables_filter input').addClass("form-control input-small"); // modify table search input
    jQuery('.dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
    jQuery('.dataTables_length select').select2(); // initialize select2 dropdown

    ///////////////////////////////////////////////

//    usersTable.on('draw.dt', function() {
////    alert( 'Redraw occurred at: '+new Date().getTime() );
//        $('#usersTable tbody tr').click(function() {
//            alert($(this).find('td').first().text());
//            $('#profileModal').modal('show');
//        });
//    });
    ///////////////////////////////////////////////////////////////
    // begin first table
    var oTable = $('#sample_1').dataTable({
        "aoColumnDefs": [
            {"bSortable": false, "aTargets": [0]}
        ],
        "aaSorting": [[1, 'asc']],
        "aLengthMenu": [
            [5, 15, 20, -1],
            [5, 15, 20, "All"] // change per page values here
        ],
        // set the initial value
        "iDisplayLength": 10,
    });

    jQuery('#sample_1_wrapper .dataTables_filter input').addClass("form-control input-small"); // modify table search input
    jQuery('#sample_1_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
    jQuery('#sample_1_wrapper .dataTables_length select').select2(); // initialize select2 dropdown

});

function showUserDetails(id) {
    $('#profileModal').modal('show');
}


