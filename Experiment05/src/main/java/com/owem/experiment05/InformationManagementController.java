package com.owem.experiment05;

import com.owem.experiment05.beans.DevelopLanguageBean;
import com.owem.experiment05.beans.StudentBean;
import com.owem.experiment05.dao.DevelopLanguageManage;
import com.owem.experiment05.dao.StudentManage;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InformationManagementController {
    private final ArrayList<CheckBox> developLanguageCheckBoxList = new ArrayList<>();
    private final ArrayList<CheckBox> hobbyCheckBoxList = new ArrayList<>();
    private final ArrayList<String> originRegionNameList = new ArrayList<>(Arrays.asList("华东", "华南", "华北", "华中", "西北", "西南", "东北"));
    private final ArrayList<String> hobbyList = new ArrayList<>(Arrays.asList("旅游", "美食", "体育", "音乐", "阅读", "其他"));
    private final DevelopLanguageManage dlm = new DevelopLanguageManage();
    private final StudentManage sm = new StudentManage();
    private final List<DevelopLanguageBean> developLanguageBeanList = dlm.getDevelopLanguages();
    public TextField searchTextField;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField phoneNumberTextField;
    public TextField addressTextField;
    public VBox developmentLanguageVBox;
    public VBox hobbyVBox;
    public ChoiceBox<String> originRegionChoiceBox;
    public RadioButton maleRadioButton;
    public RadioButton femaleRadioButton;
    private boolean isInit = false;


    protected void paneInit() {
        for (String s : originRegionNameList) {
            originRegionChoiceBox.getItems().add(s);
        }

        for (String s : hobbyList) {
            CheckBox cb = new CheckBox(s);
            hobbyCheckBoxList.add(cb);
            hobbyVBox.getChildren().add(cb);
        }

        for (DevelopLanguageBean language : developLanguageBeanList) {
            CheckBox cb = new CheckBox(language.getLanguage_name());
            developLanguageCheckBoxList.add(cb);
            developmentLanguageVBox.getChildren().add(cb);
        }
        setUsability(false);
    }


    protected void setUsability(boolean flag) {
        flag = !flag;
        idTextField.setText("");
        idTextField.setDisable(flag);
        nameTextField.setText("");
        nameTextField.setDisable(flag);
        phoneNumberTextField.setText("");
        phoneNumberTextField.setDisable(flag);
        addressTextField.setText("");
        addressTextField.setDisable(flag);
        originRegionChoiceBox.setValue("");
        originRegionChoiceBox.setDisable(flag);
        maleRadioButton.setSelected(false);
        maleRadioButton.setDisable(flag);
        femaleRadioButton.setSelected(false);
        femaleRadioButton.setDisable(flag);

        for (CheckBox cb : hobbyCheckBoxList) {
            cb.setSelected(false);
            cb.setDisable(flag);
        }

        for (CheckBox cb : developLanguageCheckBoxList) {
            cb.setSelected(false);
            cb.setDisable(flag);
        }
    }


    public void OnAddButton() {
        System.out.println("增");
        if (!isInit) {
            paneInit();
            isInit = true;
        }

        if (!idTextField.getText().equals("")) {
            StudentBean newStudent = new StudentBean(
                    Integer.parseInt(idTextField.getText()),
                    nameTextField.getText(),
                    maleRadioButton.isSelected(),
                    phoneNumberTextField.getText(),
                    addressTextField.getText(),
                    getEncode(0),
                    originRegionChoiceBox.getValue(),
                    getEncode(1)
            );
            sm.insertStudent(newStudent);
            setUsability(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("请输入 id ");
            alert.show();
        }
    }


    public void OnDeleteButton() {
        System.out.println("删");
        if (!isInit) {
            paneInit();
            isInit = true;
        }

        String searchText = searchTextField.getText();
        if (!searchText.equals("")) {
            if (sm.isExist(Integer.parseInt(searchText))) {
                sm.deleteStudent(Integer.parseInt(searchText));
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("不存在该学生!");
                alert.show();
            }
        }
    }


    public void OnChangeButton() {
        System.out.println("改");
        if (!isInit) {
            paneInit();
            isInit = true;
        }

        String searchText = searchTextField.getText();
        if (!searchText.equals("")) {
            if (sm.isExist(Integer.parseInt(searchText))) {
                StudentBean newStudent = new StudentBean(
                        Integer.parseInt(idTextField.getText()),
                        nameTextField.getText(),
                        maleRadioButton.isSelected(),
                        phoneNumberTextField.getText(),
                        addressTextField.getText(),
                        getEncode(0),
                        originRegionChoiceBox.getValue(),
                        getEncode(1)
                );
                sm.updateStudent(newStudent);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("不存在该学生!");
                alert.show();
            }
        }
    }


    public void OnCheckButton() {
        System.out.println("查");
        if (!isInit) {
            paneInit();
            isInit = true;
        }

        setUsability(true);
        String searchText = searchTextField.getText();
        if (!searchText.equals("")) {
            if (sm.isExist(Integer.parseInt(searchText))) {
                StudentBean student = sm.getStudent(Integer.parseInt(searchText));
                updateInfo(student);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("不存在该学生!");
                alert.show();
            }
        }
    }

    private void updateInfo(StudentBean student) {
        idTextField.setText(String.valueOf(student.getId()));
        nameTextField.setText(student.getFull_name());
        phoneNumberTextField.setText(student.getPhone_number());
        addressTextField.setText(student.getAddress());
        maleRadioButton.setSelected(student.isGender());
        femaleRadioButton.setSelected(!student.isGender());
        originRegionChoiceBox.setValue(student.getArea());

        int sum = student.getHobby();
        for (CheckBox cb : hobbyCheckBoxList) {
            cb.setSelected(sum % 2 == 1);
            sum = sum / 2;
        }

        sum = student.getDevelop_language();
        for (CheckBox cb : developLanguageCheckBoxList) {
            cb.setSelected(sum % 2 == 1);
            sum = sum / 2;
        }
    }

    private int getEncode(int choice) {
        int sum = 0;
        int index = 0;
        switch (choice) {
            case 0 -> {
                for (CheckBox cb : hobbyCheckBoxList) {
                    if (cb.isSelected()) {
                        sum += Math.pow(2, index);
                    }
                    index++;
                }
            }
            case 1 -> {
                for (CheckBox cb : developLanguageCheckBoxList) {
                    if (cb.isSelected()) {
                        sum += Math.pow(2, index);
                    }
                    index++;
                }
            }
        }
        return sum;
    }
}