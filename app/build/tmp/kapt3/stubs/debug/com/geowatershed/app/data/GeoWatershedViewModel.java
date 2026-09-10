package com.geowatershed.app.data;

/**
 * Backed by a local Room database (see `data/db/`) — every action here is
 * real and persists across app restarts. There is no remote backend yet:
 * captures/interventions/photos all live only on this device until one is
 * wired up, which is why the dashboard's "queued offline" count is simply
 * the number of local records that have never been synced anywhere.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\u0018\u0000 r2\u00020\u0001:\u0001rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\b\u0010M\u001a\u0004\u0018\u00010NJ\u000e\u0010O\u001a\u00020H2\u0006\u0010P\u001a\u00020/J*\u0010Q\u001a\u00020H2\u0006\u0010R\u001a\u00020\u00062\u0006\u0010S\u001a\u00020J2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020H0UJ\u0016\u0010V\u001a\u00020H2\u0006\u0010W\u001a\u00020\u00062\u0006\u0010X\u001a\u00020\u0006J*\u0010Y\u001a\u00020H2\u0006\u0010R\u001a\u00020\u00062\u0006\u0010S\u001a\u00020J2\u0012\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020H0UJ\u0018\u0010[\u001a\u0004\u0018\u00010\u00112\u0006\u0010\\\u001a\u00020JH\u0086@\u00a2\u0006\u0002\u0010]J\u0006\u0010^\u001a\u00020HJ\u0016\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010/0`2\u0006\u0010\\\u001a\u00020JJ\u0010\u0010a\u001a\u00020H2\b\u0010M\u001a\u0004\u0018\u00010NJ\u001a\u0010b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020c0\u00100`2\u0006\u0010I\u001a\u00020JJ\u0006\u0010d\u001a\u00020HJ\u0006\u0010e\u001a\u00020HJ \u0010f\u001a\u0004\u0018\u00010g2\u0006\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020iH\u0082@\u00a2\u0006\u0002\u0010kJ\u000e\u0010l\u001a\u00020JH\u0086@\u00a2\u0006\u0002\u0010mJ\u000e\u0010n\u001a\u00020H2\u0006\u0010R\u001a\u000208J\u000e\u0010o\u001a\u00020H2\u0006\u0010P\u001a\u00020/J\u000e\u0010p\u001a\u00020H2\u0006\u0010q\u001a\u00020\u0006R/\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R+\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\r\u001a\u0004\b\u0015\u0010\t\"\u0004\b\u0016\u0010\u000bR/\u0010\u0018\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\r\u001a\u0004\b\u0019\u0010\t\"\u0004\b\u001a\u0010\u000bR+\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001f\u0010\r\u001a\u0004\b\u001d\u0010\t\"\u0004\b\u001e\u0010\u000bR/\u0010!\u001a\u0004\u0018\u00010 2\b\u0010\u0005\u001a\u0004\u0018\u00010 8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b&\u0010\r\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R+\u0010(\u001a\u00020\'2\u0006\u0010\u0005\u001a\u00020\'8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b-\u0010\r\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001d\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0013R+\u00101\u001a\u00020\'2\u0006\u0010\u0005\u001a\u00020\'8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b3\u0010\r\u001a\u0004\b1\u0010*\"\u0004\b2\u0010,R\u000e\u00104\u001a\u000205X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u00109\u001a\u0002082\u0006\u0010\u0005\u001a\u0002088F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b>\u0010\r\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R+\u0010?\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\bB\u0010\r\u001a\u0004\b@\u0010\t\"\u0004\bA\u0010\u000bR+\u0010C\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\bF\u0010\r\u001a\u0004\bD\u0010\t\"\u0004\bE\u0010\u000b\u00a8\u0006s"}, d2 = {"Lcom/geowatershed/app/data/GeoWatershedViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "<set-?>", "", "capturedPhotoPath", "getCapturedPhotoPath", "()Ljava/lang/String;", "setCapturedPhotoPath", "(Ljava/lang/String;)V", "capturedPhotoPath$delegate", "Landroidx/compose/runtime/MutableState;", "captures", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/geowatershed/app/data/db/CaptureEntity;", "getCaptures", "()Lkotlinx/coroutines/flow/StateFlow;", "description", "getDescription", "setDescription", "description$delegate", "governanceLoginError", "getGovernanceLoginError", "setGovernanceLoginError", "governanceLoginError$delegate", "governanceOfficerName", "getGovernanceOfficerName", "setGovernanceOfficerName", "governanceOfficerName$delegate", "Lcom/geowatershed/app/data/GpsFix;", "gpsFix", "getGpsFix", "()Lcom/geowatershed/app/data/GpsFix;", "setGpsFix", "(Lcom/geowatershed/app/data/GpsFix;)V", "gpsFix$delegate", "", "gpsSearching", "getGpsSearching", "()Z", "setGpsSearching", "(Z)V", "gpsSearching$delegate", "interventions", "Lcom/geowatershed/app/data/db/InterventionEntity;", "getInterventions", "isGovernanceAuthed", "setGovernanceAuthed", "isGovernanceAuthed$delegate", "locationProvider", "Lcom/geowatershed/app/data/LocationProvider;", "repository", "Lcom/geowatershed/app/data/GeoWatershedRepository;", "Lcom/geowatershed/app/data/model/ObservationType;", "selectedObservation", "getSelectedObservation", "()Lcom/geowatershed/app/data/model/ObservationType;", "setSelectedObservation", "(Lcom/geowatershed/app/data/model/ObservationType;)V", "selectedObservation$delegate", "watershedName", "getWatershedName", "setWatershedName", "watershedName$delegate", "watershedSub", "getWatershedSub", "setWatershedSub", "watershedSub$delegate", "addPhoto", "", "interventionId", "", "set", "Lcom/geowatershed/app/data/model/PhotoSet;", "file", "Ljava/io/File;", "advanceIntervention", "intervention", "approveIntervention", "type", "sourceCaptureId", "onApproved", "Lkotlin/Function1;", "attemptGovernanceLogin", "officerId", "password", "createIntervention", "onCreated", "getCapture", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "governanceLogout", "interventionFlow", "Lkotlinx/coroutines/flow/Flow;", "onPhotoCaptured", "photosFor", "Lcom/geowatershed/app/data/db/PhotoEvidenceEntity;", "refreshLocation", "resolveWatershedLocation", "reverseGeocode", "Landroid/location/Address;", "latitude", "", "longitude", "(DDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveAndAnalyze", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectObservation", "submitForVerification", "updateDescription", "text", "Companion", "app_debug"})
public final class GeoWatershedViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.geowatershed.app.data.GeoWatershedRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.geowatershed.app.data.LocationProvider locationProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState watershedName$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState watershedSub$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isGovernanceAuthed$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState governanceOfficerName$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState governanceLoginError$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.geowatershed.app.data.db.CaptureEntity>> captures = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.geowatershed.app.data.db.InterventionEntity>> interventions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState selectedObservation$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState description$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState gpsFix$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState gpsSearching$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState capturedPhotoPath$delegate = null;
    public static final int EVIDENCE_MINIMUM_PER_SET = 2;
    @org.jetbrains.annotations.NotNull()
    public static final com.geowatershed.app.data.GeoWatershedViewModel.Companion Companion = null;
    
    public GeoWatershedViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWatershedName() {
        return null;
    }
    
    private final void setWatershedName(java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWatershedSub() {
        return null;
    }
    
    private final void setWatershedSub(java.lang.String p0) {
    }
    
    public final void resolveWatershedLocation() {
    }
    
    private final java.lang.Object reverseGeocode(double latitude, double longitude, kotlin.coroutines.Continuation<? super android.location.Address> $completion) {
        return null;
    }
    
    public final boolean isGovernanceAuthed() {
        return false;
    }
    
    private final void setGovernanceAuthed(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGovernanceOfficerName() {
        return null;
    }
    
    private final void setGovernanceOfficerName(java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getGovernanceLoginError() {
        return null;
    }
    
    private final void setGovernanceLoginError(java.lang.String p0) {
    }
    
    public final void attemptGovernanceLogin(@org.jetbrains.annotations.NotNull()
    java.lang.String officerId, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    public final void governanceLogout() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.geowatershed.app.data.db.CaptureEntity>> getCaptures() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.geowatershed.app.data.db.InterventionEntity>> getInterventions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.geowatershed.app.data.db.PhotoEvidenceEntity>> photosFor(long interventionId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.geowatershed.app.data.db.InterventionEntity> interventionFlow(long id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.geowatershed.app.data.model.ObservationType getSelectedObservation() {
        return null;
    }
    
    private final void setSelectedObservation(com.geowatershed.app.data.model.ObservationType p0) {
    }
    
    public final void selectObservation(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.model.ObservationType type) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    private final void setDescription(java.lang.String p0) {
    }
    
    public final void updateDescription(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.geowatershed.app.data.GpsFix getGpsFix() {
        return null;
    }
    
    private final void setGpsFix(com.geowatershed.app.data.GpsFix p0) {
    }
    
    public final boolean getGpsSearching() {
        return false;
    }
    
    private final void setGpsSearching(boolean p0) {
    }
    
    public final void refreshLocation() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCapturedPhotoPath() {
        return null;
    }
    
    private final void setCapturedPhotoPath(java.lang.String p0) {
    }
    
    public final void onPhotoCaptured(@org.jetbrains.annotations.Nullable()
    java.io.File file) {
    }
    
    /**
     * Persists the current capture-screen state as a new [CaptureEntity] and resets the form.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveAndAnalyze(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCapture(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.geowatershed.app.data.db.CaptureEntity> $completion) {
        return null;
    }
    
    public final void advanceIntervention(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.InterventionEntity intervention) {
    }
    
    public final void submitForVerification(@org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.db.InterventionEntity intervention) {
    }
    
    public final void createIntervention(@org.jetbrains.annotations.NotNull()
    java.lang.String type, long sourceCaptureId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onCreated) {
    }
    
    /**
     * Governance officer approval: creates the intervention already in the Approved stage, skipping Proposed.
     */
    public final void approveIntervention(@org.jetbrains.annotations.NotNull()
    java.lang.String type, long sourceCaptureId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onApproved) {
    }
    
    public final void addPhoto(long interventionId, @org.jetbrains.annotations.NotNull()
    com.geowatershed.app.data.model.PhotoSet set, @org.jetbrains.annotations.Nullable()
    java.io.File file) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/geowatershed/app/data/GeoWatershedViewModel$Companion;", "", "()V", "EVIDENCE_MINIMUM_PER_SET", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}