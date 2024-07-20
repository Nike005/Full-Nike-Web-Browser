package org.htmlcleaner;

class ProxyTagNode extends TagNode {
    private TagNode bodyNode;
    private CommentNode comment;
    private ContentNode token;

    public TagNode getParent() {
        return null;
    }

    public ProxyTagNode(ContentNode contentNode, TagNode tagNode) {
        super("");
        this.token = contentNode;
        this.bodyNode = tagNode;
    }

    public ProxyTagNode(CommentNode commentNode, TagNode tagNode) {
        super("");
        this.comment = commentNode;
        this.bodyNode = tagNode;
    }

    public boolean removeFromTree() {
        this.bodyNode.removeChild(getToken());
        return true;
    }

    public Object getToken() {
        ContentNode contentNode = this.token;
        return contentNode != null ? contentNode : this.comment;
    }

    public String getContent() {
        ContentNode contentNode = this.token;
        return contentNode != null ? contentNode.getContent() : this.comment.getContent();
    }
}
