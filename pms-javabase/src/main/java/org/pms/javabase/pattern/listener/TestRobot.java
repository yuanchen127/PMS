package org.pms.javabase.pattern.listener;

public class TestRobot {
    public static void main(String[] args) {
        EventListener listener = new RobotEventListener();
        Robot robot = new Robot(listener);
        robot.work();
    }

}
