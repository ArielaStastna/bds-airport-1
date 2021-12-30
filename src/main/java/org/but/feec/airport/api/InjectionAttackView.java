package org.but.feec.airport.api;

public class InjectionAttackView {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Long id;
    private String content;

    @Override
    public String toString() {
        return "SQLInjectionView{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
