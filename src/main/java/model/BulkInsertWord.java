package model;

import java.util.List;

public class BulkInsertWord {
        private List<WordMeaningDto> words;

        // getters and setters

    public List<WordMeaningDto> getWords() {
        return words;
    }

    public void setWords(List<WordMeaningDto> words) {
        this.words = words;
    }

    public static class WordMeaningDto {
            private String firstName;
            private String lastName;
            private String meaning;
            private String mobile;
            private Boolean show_for_others;
            private String word;

            // getters and setters

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getMeaning() {
            return meaning;
        }

        public String getMobile() {
            return mobile;
        }

        public Boolean getShow_for_others() {
            return show_for_others;
        }

        public String getWord() {
            return word;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setMeaning(String meaning) {
            this.meaning = meaning;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setShow_for_others(Boolean show_for_others) {
            this.show_for_others = show_for_others;
        }

        public void setWord(String word) {
            this.word = word;
        }
    }

}
