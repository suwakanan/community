package cn.yohane.community.dto;

import cn.yohane.community.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuwaKanan on 2020/06/09
 */
// 包裹页面承载的元素
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();

    // 这个是弄分页的时候新加上的
    private Integer totalPage;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    // 所有页数


    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowFirstPage() {
        return showFirstPage;
    }

    public void setShowFirstPage(boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    // 设置分页
    public void setPagination(Integer totalCount, Integer page, Integer size) {

        //totalCount / size;
        //Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        /**
         * 1 1234567
         * 2 1234567
         * 3 1234567
         * 4 1234567
         * 5 2345678
         * 6 3456789
         */

        // 验证传来的page有没有不在范围内
        // 这里是有点问题的
        if (page<1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        this.page = page; // 把page变成改变后的
        // 其实应该在改变之后再查询数据库而不是查了数据库先
        // 这里好像和css样式有关


        pages.add(page);
        for (int i=1; i<=3; i++) {
            if (page-i>0) {
                // 注意参数位置，都有提示了淦
                // pages.add(page - i, 0);
                pages.add(0, page - i);

            }
            if (page+i<=totalPage) {
                pages.add(page+i);
            }
        }

        // 是否展示上一页的标识
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        // 是否展示下一页的标识
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        // 是否展示第一页的标识
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        // 是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }

}
