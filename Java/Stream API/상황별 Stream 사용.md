1. List와 List의 비교

```java

List<MailCategory> allUserCategories = subscriptionApiService.getAllUserCategories();

/**
  1. filter를 이용하여 특정사용자의  id와 해당사용자의 id, 카테고리 id를 가지는 객체를 비교하여 List로 뽑아냄 (List<MailCategory>가 return 됨)
  2. map을 이용하여 특정 사용자가 가지는 카테고리 id의 List를 뽑아낸다. (List<Integer>가 return 됨)
*/
List<Integer> userCategories = allUserCategories.stream()
                    .filter(uc -> uc.getUserId().equals(maintUser.getId().intValue()))
                    .map(uc -> uc.getCategoryId())
                    .collect(Collectors.toList());

/**
  request로 받아오는 category와 해당 user의 카테고리를 비교하여 match되는 category를 Set에 담아준다.
    userCategories.stream().anyMatch(Predicate.isEqual(id))
      => user의 Category와 비교하여 anyMatch에서 Boolean를 뱉어주어 해당 id를 List에 저장시킨다.
      => filter 내부에서 stream을 이용하여 list를 검색
*/
Set<Integer> matchedCategories = parentCategories.stream()
        .filter(id -> userCategories.stream().anyMatch(Predicate.isEqual(id)))
        .collect(Collectors.toSet());
```
