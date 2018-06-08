package entity.jsp.tag.base;

public interface iSelectOption {
  /**
   * The value for the selected option.
   * 
   * @return String
   */
  public <O extends Object> O getOptionValue();
  /**
   * The display label for the select options.
   * 
   * @return 
   */
  public <O extends Object> O getOptionLabel();
}