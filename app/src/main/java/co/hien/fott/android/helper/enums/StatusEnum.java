package co.hien.fott.android.helper.enums;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public enum StatusEnum {
    SUCCESS(){
        public String toString() {
            return "success";
        }
    },
    FAILED(){
        public String toString() {
            return "failed";
        }
    }

}
