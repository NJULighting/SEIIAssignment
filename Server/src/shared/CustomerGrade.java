package shared;

public enum CustomerGrade {
    ONE("一级"), TWO("二级"), THREE("三级"), FOUR("四级"), FIVE("五级");

    private String chineseRepresentation;

    CustomerGrade(String s) {
        chineseRepresentation = s;
    }

    public static CustomerGrade get(String name) {
        for (CustomerGrade grade : values()) {
            if (grade.chineseRepresentation.contains(name)) {
                return grade;
            }
        }
        return null;
    }
}
