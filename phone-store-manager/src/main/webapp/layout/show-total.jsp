<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--show-total-->
<div class="row">
    <div class="col-12 col-lg-6 col-xl-3">
        <div class="widget widget-tile">
            <div class="data-info">
                <div class="desc">Tổng nhân viên</div>
                <div class="value">
                    <span class="number text-success" data-toggle="counter">
                     <%=request.getParameter("usersTotal") %>
                    </span>
                </div>
            </div>
        </div>
    </div>
    <div class="col-12 col-lg-6 col-xl-3">
        <div class="widget widget-tile">
            <div class="data-info">
                <div class="desc">Tổng đơn hàng</div>
                <div class="value">
                    <span class="number text-success" data-toggle="counter">
                        <%=request.getParameter("ordersTotal") %>
                    </span>
                </div>
            </div>
        </div>
    </div>
    <div class="col-12 col-lg-6 col-xl-3">
        <div class="widget widget-tile">
            <div class="data-info">
                <div class="desc">Số sản phẩm</div>
                <div class="value">
                    <span class="number text-success" data-toggle="counter">
                    <%=request.getParameter("productsTotal") %>
                    </span>
                </div>
            </div>
        </div>
    </div>
    <div class="col-12 col-lg-6 col-xl-3">
        <div class="widget widget-tile">
            <div class="data-info">
                <div class="desc">Tổng khách hàng</div>
                <div class="value">
                    <span class="number text-success" data-toggle="counter">
                    <%=request.getParameter("customersTotal") %>
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
<!--show-total-->

