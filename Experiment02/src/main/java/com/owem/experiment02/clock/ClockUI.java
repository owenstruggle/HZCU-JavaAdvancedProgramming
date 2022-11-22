package com.owem.experiment02.clock;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Owem
 * @date 2022/9/20 16:04
 * @description TODO
 **/
public class ClockUI extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = new AnchorPane();
        Canvas canvas = new Canvas(800, 650);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 800, 650);
        stage.setTitle("时钟页面");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // 获取画板对象
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // 创建时间轴
        Timeline timeLine = new Timeline();
        // 获取时间轴的帧列表
        ObservableList<KeyFrame> keyFrames = timeLine.getKeyFrames();
        // 添加关键帧
        keyFrames.add(new KeyFrame(Duration.seconds(0.1), e -> {
            // 刷新操作
            gc.clearRect(0, 0, 800, 650);
            // 绘制表盘
            dials(gc);
            // 绘制刻度
            scale(gc);
            // 绘制指针
            point(gc);
        }));
        // 设置时间轴播放次数为无限
        timeLine.setCycleCount(-1);
        // 播放时间轴
        timeLine.play();
    }

    /**
     * 绘制表盘
     */
    public void dials(GraphicsContext gc) {
        // 保存现场
        gc.save();
        // 变换坐标到外切圆矩形左上角坐标
        gc.translate(100, 25);
        gc.setLineWidth(8);
        gc.setStroke(Color.GRAY);
        gc.strokeOval(0, 0, 600, 600);
        gc.restore();
    }

    /**
     * 绘制刻度
     */
    public void scale(GraphicsContext gc) {
        // 保存现场
        gc.save();
        // 变换坐标系原点到表盘中心
        gc.translate(400, 325);
        // 坐标逆时针旋转角度-90
        gc.rotate(-90);
        // 设置字体大小
        gc.setFont(Font.font(20));
        for (int i = 1; i < 61; i++) {
            // 每一个刻度角度为6度
            gc.rotate(6);
            if (i % 5 == 0) {
                gc.save();
                // 当前坐标切换到 (250,0) 即刻度左边界位置
                gc.translate(250, 0);
                // 设置表格数字位置 相对于桌面应该是竖直
                gc.rotate(90 - i / 5 * 30);
                gc.fillText(i / 5 + "", 0, 0);
                gc.restore();
                gc.fillRect(275, 0, 22, 10);
            } else {
                gc.fillRect(285, 0, 12, 5);
            }
        }
        // 恢复现场
        gc.restore();
    }

    /**
     * 绘制指针
     */
    public void point(GraphicsContext gc) {
        LocalDateTime time = LocalDateTime.now();
        int seconds = time.getSecond();
        int minutes = time.getMinute();
        int hours = time.getHour();
        double[] pointX1 = new double[]{0, 50, 270, 50};
        double[] pointY1 = new double[]{0, 5, 0, -5};
        double[] pointX2 = new double[]{0, 30, 210, 30};
        double[] pointY2 = new double[]{0, 10, 0, -10};
        double[] pointX3 = new double[]{0, 20, 150, 20};
        double[] pointY3 = new double[]{0, 12, 0, -12};
        gc.save();

        // 坐标移动至圆心
        gc.translate(400, 325);
        // 时间数字
        {
            String timeText1 = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            gc.setFill(Paint.valueOf("#c0c0c0"));
            gc.setFont(Font.font(20));
            gc.fillText(timeText1, -40, -200);
            String timeText2 = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            gc.setFill(Paint.valueOf("#c0c0c0"));
            gc.setFont(Font.font(115));
            gc.fillText(timeText2, -220, 30);
        }
        // 秒钟
        {
            gc.save();
            gc.rotate(-90);
            gc.setFill(Color.RED);
            gc.rotate(seconds * 6);
            // 四边形秒钟
            gc.fillPolygon(pointX1, pointY1, 4);
            gc.restore();
        }
        // 分钟
        {
            gc.save();
            gc.rotate(-90);
            gc.setFill(Color.BLUE);
            gc.rotate(minutes * 6 + 0.1 * seconds);
            // 四边形分钟
            gc.fillPolygon(pointX2, pointY2, 4);
            gc.restore();
        }
        // 时钟
        {
            gc.save();
            gc.rotate(-90);
            gc.setFill(Color.BLACK);
            gc.rotate(hours * 30 + minutes * 0.5 + seconds * (0.5 / 60));
            // 四边形时钟
            gc.fillPolygon(pointX3, pointY3, 4);
            gc.restore();
        }
        // 恢复现场
        gc.restore();
    }
}