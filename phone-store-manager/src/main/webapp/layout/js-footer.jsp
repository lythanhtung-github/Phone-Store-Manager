<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- js-footer -->
<script src="/assets\lib\jquery\jquery.min.js" type="text/javascript"></script>
<script src="/assets\lib\perfect-scrollbar\js\perfect-scrollbar.min.js" type="text/javascript"></script>
<script src="/assets\lib\bootstrap\dist\js\bootstrap.bundle.min.js" type="text/javascript"></script>
<script src="/assets\js\app.js" type="text/javascript"></script>
<script src="/assets\lib\jquery-flot\jquery.flot.js" type="text/javascript"></script>
<script src="/assets\lib\jquery-flot\jquery.flot.pie.js" type="text/javascript"></script>
<script src="/assets\lib\jquery-flot\jquery.flot.time.js" type="text/javascript"></script>
<script src="/assets\lib\jquery-flot\jquery.flot.resize.js" type="text/javascript"></script>
<script src="/assets\lib\jquery-flot\plugins\jquery.flot.orderBars.js" type="text/javascript"></script>
<script src="/assets\lib\jquery-flot\plugins\curvedLines.js" type="text/javascript"></script>
<script src="/assets\lib\jquery-flot\plugins\jquery.flot.tooltip.js" type="text/javascript"></script>
<!--index-->
<c:if test="${param == 'index'}">
    <script src="/assets\lib\jquery.sparkline\jquery.sparkline.min.js" type="text/javascript"></script>
    <script src="/assets\lib\countup\countUp.min.js" type="text/javascript"></script>
</c:if>
<!--index-->

<script src="/assets\lib\jquery-ui\jquery-ui.min.js" type="text/javascript"></script>

<!--index-->
<c:if test="${param == 'index'}">
    <script src="/assets\lib\jqvmap\jquery.vmap.min.js" type="text/javascript"></script>
    <script src="/assets\lib\jqvmap\maps\jquery.vmap.world.js" type="text/javascript"></script>
</c:if>
<!--index-->
<script type="text/javascript">
    $(document).ready(function () {
        App.init();
        App.pageProfile();
    });
</script>
<!-- js-footer -->