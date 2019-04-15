package hw4.properties;

public class Project {
    private String projectName;
    private String projectStatus;
    private boolean inheritGlobalCategories;
    private String viewStatus;
    private String description;

    public Project(String projectName, String projectStatus, boolean inheritGlobalCategories, String viewStatus, String description)
    {
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        this.inheritGlobalCategories = inheritGlobalCategories;
        this.viewStatus = viewStatus;
        this.description = description;
    }

    public String getProjectName()
    {
        return projectName;
    }
    public String getProjectStatus(){return projectStatus;}
    public boolean getInheritGlobalCategories(){return inheritGlobalCategories;}
    public String getViewStatus(){return viewStatus;}
    public String getDescription(){return description;}
}
