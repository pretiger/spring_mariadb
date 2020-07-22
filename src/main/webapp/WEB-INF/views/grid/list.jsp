<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

  <table id="list"><tr><td></td></tr></table> 
  <div id="pager"></div> 
  <script>
    var dataArray = [
      {
        "name": "Lorene Battle",
        "phone": "(936) 574-3976"
      },
      {
        "name": "Wendi Downs",
        "phone": "(815) 510-3017"
      }
    ];
 
    $(document).ready(function() {
      $("#list").jqGrid({
        datatype: 'local',
        styleUI: 'Foundation',
        data: dataArray,
        colModel: [
          {name: 'name', label : 'Name'},
          {name: 'phone', label : 'Phone Number'}
        ],
        caption : 'Users Grid',
        height: 'auto',
        rowNum: 5,
        pager: '#pager'
      });
    });
  </script>