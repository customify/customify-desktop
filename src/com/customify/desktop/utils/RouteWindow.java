package com.customify.desktop.utils;

import com.customify.cli.Colors;
import com.customify.cli.dashboards.BusinessAdminDashboard;
import com.customify.cli.dashboards.EmployeeDashboard;
import com.customify.cli.dashboards.SuperAdminDashboard;

import java.net.Socket;

public class RouteWindow {
    public  RouteWindow(String appUser, Socket socket) throws Exception{
        switch (appUser) {
            case "BUSINESS_ADMIN":
                BusinessAdminDashboard bussDashboard = new BusinessAdminDashboard(socket);
                break;
            case "EMPLOYEE":
                EmployeeDashboard empDashboard = new EmployeeDashboard(socket);

                break;
            case "SUPER_ADMIN":
                SuperAdminDashboard admDashboard = new SuperAdminDashboard(socket);
                break;
            default:
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tINVALID CHOICE"+ Colors.ANSI_RESET);
        }
    }
}
