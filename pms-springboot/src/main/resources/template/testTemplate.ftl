package ${package};

public class ${class?cap_first} {

    private ${info.type} ${info.name};

    public ${info.type} get${info.name?cap_first}() {
        return ${info.name};
    }

    public void set${info.name?cap_first}(${info.type} ${info.name}) {
        this.${info.name} = ${info.name};
    }
}