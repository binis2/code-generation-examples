# code-generation-annotation

Annotation processor for Binis CodeGen Library.

### Maven Dependency
```xml
    <dependency>
        <groupId>dev.binis</groupId>
        <artifactId>code-generator-annotation</artifactId>
        <version>0.2.0</version>
    </dependency>
```

![IntelliJ Idea Setup](/images/annotation-processing.png "Region Folding setup in IntelliJ")

# Region Enricher

![IntelliJ Idea Setup](/images/regionfolding.png "Region Folding setup in IntelliJ")

![IntelliJ Idea Setup](/images/regionfoldingexample.png "Region Folding example in IntelliJ")

# Joins on @OneToOne relations

```java
    var account = Account.find().by()
            .id(12345L).and()
            .user().fetch()
        .get();
    var user = BusinessUser.find().by()
            .id(session.getCurrentUser().getId()).and()
            .partner().leftFetch()
        .get();
```

# Static projections

```java
BusinessAgreement.find().by().projection(BusinessAgreementView.class).list();
```

```java
public interface BusinessAgreementView extends Identifiable {

    @JsonSerialize(using = ToStringSerializer.class)
    Long getId();
    String getTitle();
    String getDescription();
    String getLink();

    @JsonProperty("icon")
    String getIconUrl();

}
```

```postgres-sql
select u.description as description,u.icon.url as iconUrl,u.id as id,u.link as link,u.title as title from com.spiralbank.business.core.objects.BusinessAgreement u
```

# References

```java
    var accounts = Account.find().by().user(user).references();
    var user = User.find().by().id(12345L).reference();
```

# Asynchronous flows

```java
    BusinessEvent.find().asyncC("statements", f -> {
        ...
    });
```

# Asynchronous CompletableFeature

```java
    log.info("Simple async save:");
    var job1 = User.find().by().username("binis").ensure().with().modified(Time.now()).async().save();
    log.info("Note: In this case the query is executed in the main thread, just the save operation is done in another!");
    log.info("Note: You need to use AsyncEntityModifier as your base modifier class!");

    log.info("Async lambda:");
    var job2 = User.find().async(t -> {
        log.info("Note: In this case everything is executed in different thread. Async also envelops the lambda with transaction!");
        return t.by().username("binis").get();
    });

    log.info("Now we wait!");
    var time = System.currentTimeMillis();
    CompletableFuture.allOf(job1, job2).join();
    log.info("Done ({}) (Took {} ms.)!", job2.get(), System.currentTimeMillis() - time);
    log.warn("WARNING: You can change the default thread pool executor with something like this - CodeExecutor.registerDefaultExecutor(CodeExecutor.syncExecutor());");
    log.warn("WARNING: or change the thread pool executor per flow - CodeExecutor.registerExecutor(\"MySpecialFlow\", CodeExecutor.syncExecutor());");
```

# Query fragments.

```java
    @QueryFragment
    default void fetchWithPartner(Long userId) {
        Preset.declare().field(id(), userId).and().prototype(partner()).fetch();
    }

    BusinessUser.find().by().__fetchWithPartner(user.getId()).get();
```

```java
@CodePrototype(generateImplementation = false, interfaceSetters = false)
public interface ActivatablePrototype {

    @Column(nullable = false)
    @ColumnDefault("true")
    @Default("true")
    boolean active();

    @SanitizeLambda("v -> v == null ? Time.of(0L) : v")
    @ColumnDefault("'1970-01-01 00:00:00'")
    @Default("Time.of(0L)")
    OffsetDateTime activeFrom();

    @SanitizeLambda("v -> v == null ? Time.of(4000, 1, 1) : v")
    @ColumnDefault("'4000-01-01 00:00:00'")
    @Default("Time.of(4000, 1, 1)")
    OffsetDateTime activeTo();

    @CodeImplementation(value = "return Time.of(0L).equals(activeFrom()) ? null : activeFrom();", imports = {"com.spiralbank.common.core.tools.Time"})
    default OffsetDateTime getActiveFrom() {
        return Time.of(0L).equals(activeFrom()) ? null : activeFrom();
    }

    @CodeImplementation("return Time.of(4000, 1, 1).equals(activeTo()) ? null : activeTo();")
    default OffsetDateTime getActiveTo() {
        return Time.of(4000, 1, 1).equals(activeTo()) ? null : activeTo();
    }

    @Keep
    default boolean isActuallyActive() {
        var time = Time.now();
        return active() && time.isAfter(activeFrom()) && time.isBefore(activeTo());
    }

    @QueryFragment
    default String __isActive() {
        return "active(true).and().script(\"CURRENT_TIMESTAMP between active_from and active_to\")";
    }

}
```

```java
    @QueryFragment
    default void isRegistration() {
        Preset.declare().field(type(), BusinessApplicationType.REGISTRATION);
    }
    ...
    BusinessUserApplication.find().by()
        .__isRegistration().and()
        .user().fetch().and()
        .inviter(user).and()
        .seen().less(3)
        .list().forEach(app -> {
            cards.add(UpdateCenterCard.create()
                .header()
                    .icon("https://cdn.zeplin.io/5e3b1c818f31c4b3e2064abd/assets/AE8C60BA-CB62-4B46-87B8-EC9F8E14BDF2.png")
                    .title(app.getUser().getFirstName() + " accepted your invite and is now on the Spiral Giving Network!")
                .done()
            .done());
            app.with()
                .seen(app.getSeen() + 1)
                .async().flow("update-center").save();
        });
```

# Custom validations

```java
    @CodeAnnotation
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Validate(value = RegExValidator.class, params = ValidateJavaScriptInjection.REGEX, message = "(%s) Invalid value!")
    public @interface ValidateJavaScriptInjection {
        String REGEX = "^((?!<).)*$";
    }
```

```java
    @CodeAnnotation
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Validate(value = RegExValidator.class, params = ValidatePhone.REGEX, message = "(%s) Invalid phone number!")
    public @interface ValidatePhone {
        String REGEX = "^(\\([0-9]{3}\\) |[0-9]{3}-)[0-9]{3}-[0-9]{4}$";
    }
```

# Grouping 

```java
    Account.find().aggregate()
            .group().active().and()
            .min().available().and()
            .max().available().and()
            .sum().available().and()
            .avg().available().where().balance().greater(0.0).tuples().forEach(this::printTuple);
```

# Distinct change

```java
    Transaction.find().aggregate().distinct().account().where().account().type(AccountType.EXTERNAL).paginated(50, Account.class, account -> {
        ... 
    });
```

# Conditional modifier

```java
    account.with()
        .name(name)
        ._if(nonNull(balance), m -> m.balance(balance))
    ...
        .done();
```

# Nested and Generic prototypes.

```java
@CodeBuilder(base = true)
public interface CardPrototype<T extends Payload> {
    
    String type();
    String schema();
    Long timestamp();
    CardHeaderPrototype header();
    T payload();
    String subType();
}
```
```java
@CodeBuilder
@Embeddable
public interface CardHeaderPrototype extends Titleable, Subtitleable {

    String icon();

    List<OptionView> options();
}
```
```java
@CodeBuilder
public interface AccountOverviewCardPrototype extends CardPrototype<AccountOverviewCardPrototype.AccountOverviewCardPayloadPrototype> {

    @CodeBuilder
    interface AccountOverviewCardPayloadPrototype extends Payload {
        int raised();
        int donated();
        int matching();
    }
}
```
```java
    AccountOverviewCard.create()
        .header()
            .icon("https://cdn.zeplin.io/5e3b1c818f31c4b3e2064abd/assets/AE8C60BA-CB62-4B46-87B8-EC9F8E14BDF2.png")
            .title(app.getUser().getFirstName() + " accepted your invite and is now on the Spiral Giving Network!")
        .done()
        .payload()
            .raised(data.getAmount() + data.getMatching())
            .donated(data.getAmount())
            .matching(data.getMatching())
        .done()
    .done()
```

