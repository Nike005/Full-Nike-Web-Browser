package org.htmlcleaner.audit;

public enum ErrorType {
    FatalTagMissing,
    NotAllowedTag,
    RequiredParentMissing,
    UnclosedTag,
    UniqueTagDuplicated,
    Deprecated,
    UnpermittedChild,
    Unknown
}
