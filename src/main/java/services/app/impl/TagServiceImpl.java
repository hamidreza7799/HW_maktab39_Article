package services.app.impl;

import base.services.impl.BaseServiceImpl;
import domains.app.Tag;
import helper.SingleTonScanner;
import repositories.app.TagRepository;
import services.app.TagService;

import java.util.HashSet;
import java.util.Set;

public class TagServiceImpl extends BaseServiceImpl<Tag,Integer,TagRepository> implements TagService {

    public TagServiceImpl(TagRepository repository){
        super(repository);
    }

    @Override
    public boolean printTags(){
        System.out.println("\t**********\tTags\t**********");
        Set<Tag> tags = this.findAll();
        for (Tag tag : tags) {
            System.out.println("Id of tag is: " + tag.getId());
            System.out.println("Title of tag is: " + tag.getTitle());
            System.out.println("\t\r-------------\t\r\t\r+++++++++++");
        }
        boolean isEmpty = tags.size() == 0;
        if (isEmpty) {
            System.out.println("We dont have any tag in data-base....");
        }
        System.out.println("\t\r**********\t\rTags\t\r**********");
        return isEmpty;
    }

    @Override
    public Tag createNewTag() {
        System.out.print("Enter the title of tag: ");
        String title = SingleTonScanner.getScanner().nextLine();
        Tag newTag = new Tag();
        newTag.setTitle(title);
        this.save(newTag);
        return newTag;
    }

    @Override
    public Set<Tag> chooseTags() {
        boolean isEmpty = this.printTags();
        Set<Tag> customTags = new HashSet<>();
        if(isEmpty)
            return customTags;
        else {
            System.out.println("Enter title of the tag(write exit for finish): ");
            String title = SingleTonScanner.getScanner().nextLine();
            while (!title.toLowerCase().equals("exit")){
                customTags.add(this.findByTitle(title));
                System.out.println("Enter title of the tag(write exit for finish): ");
                title = SingleTonScanner.getScanner().nextLine();
            }
            return customTags;
        }
    }

    @Override
    public Tag findByTitle(String title) {
        return this.repository.findByTitle(title);
    }
}
