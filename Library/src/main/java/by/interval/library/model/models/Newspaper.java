package by.interval.library.model.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Newspaper extends PrintedEdition{
    Integer issueNumber;
}
