$(document).ready(function() {
    $('#addFile').click(function() {
        var fileIndex = $('#fileTable tr').children().length;
        $('#fileTable').append(
            '<tr><td>'+
                '<div class="fileupload fileupload-new" data-provides="fileupload">' +
                    '<span class="btn btn-file" style="width: 195px;">' +
                        '<span class="fileupload-new">Select file</span>' +
                        '<span class="fileupload-exists">Change</span>' +
                        '<input type="file" name="files['+ fileIndex +']"/>' +
                    '</span>' +
                    '<span class="fileupload-preview"></span>' +
                    '<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none">×</a>' +
                '</div>' +
            '</td></tr>');
    });

});