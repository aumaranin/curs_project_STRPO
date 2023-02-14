package ru.bmstu.curs_project_strpo.client2.DataObjects;

public class Book
{
    private int id;
    private String title;
    private String author;
    private String genre;
    private int year;
    private int price;
    private int count;

    public Book()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public String toStringShort()
    {
        String result = this.author + " / " + this.title + " / " + this.genre + " / " + this.year + " / " + this.price +"р. " + this.count + "шт.";
        return result;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }
}
