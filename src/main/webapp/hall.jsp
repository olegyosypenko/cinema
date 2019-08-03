<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 03.08.2019
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/parts/header.jspf"%>
<div ng-controller="hallCtrl">
    <div>
        <h2 class="header">Halls</h2>
        <div ng-repeat="hall in halls">
            rows: {{hall.rows}}, columns: {{hall.columns}}
        </div>

    </div>
    <div>
        <ng-form ng-submit="save(hall)">
            <input type="number" name="rows" ng-model="hall.rows">
            <input type="number" name="columns" ng-model="hall.columns">
            <button ng-click="save(hall)" type="submit">save</button>
        </ng-form>
    </div>
</div>
<%@include file="WEB-INF/parts/footer.jspf"%>
