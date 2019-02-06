package mx.com.televisa.landamark.model;

import java.sql.Timestamp;

import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jan 28 13:57:50 CST 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class LmkIntMappingCatTabViewRowImpl extends ViewRowImpl {
    public static final int ENTITY_LMKINTMAPPINGCATTAB = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        IdMapping,
        NomRelation,
        NomOrigin,
        NomDestiny,
        ValValueRelation,
        ValValueOrigin,
        ValValueDestiny,
        IndSysSystem,
        IndSysOrigin,
        IndSysDestiny,
        IndUsedBy,
        IndDescription,
        IdMappingRel,
        IndEstatus,
        AttributeCategory,
        Attribute1,
        Attribute2,
        Attribute3,
        Attribute4,
        Attribute5,
        Attribute6,
        Attribute7,
        Attribute8,
        Attribute9,
        Attribute10,
        Attribute11,
        Attribute12,
        Attribute13,
        Attribute14,
        Attribute15,
        FecCreationDate,
        NumCreatedBy,
        FecLastUpdateDate,
        NumLastUpdatedBy,
        NumLastUpdateLogin;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int IDMAPPING = AttributesEnum.IdMapping.index();
    public static final int NOMRELATION = AttributesEnum.NomRelation.index();
    public static final int NOMORIGIN = AttributesEnum.NomOrigin.index();
    public static final int NOMDESTINY = AttributesEnum.NomDestiny.index();
    public static final int VALVALUERELATION = AttributesEnum.ValValueRelation.index();
    public static final int VALVALUEORIGIN = AttributesEnum.ValValueOrigin.index();
    public static final int VALVALUEDESTINY = AttributesEnum.ValValueDestiny.index();
    public static final int INDSYSSYSTEM = AttributesEnum.IndSysSystem.index();
    public static final int INDSYSORIGIN = AttributesEnum.IndSysOrigin.index();
    public static final int INDSYSDESTINY = AttributesEnum.IndSysDestiny.index();
    public static final int INDUSEDBY = AttributesEnum.IndUsedBy.index();
    public static final int INDDESCRIPTION = AttributesEnum.IndDescription.index();
    public static final int IDMAPPINGREL = AttributesEnum.IdMappingRel.index();
    public static final int INDESTATUS = AttributesEnum.IndEstatus.index();
    public static final int ATTRIBUTECATEGORY = AttributesEnum.AttributeCategory.index();
    public static final int ATTRIBUTE1 = AttributesEnum.Attribute1.index();
    public static final int ATTRIBUTE2 = AttributesEnum.Attribute2.index();
    public static final int ATTRIBUTE3 = AttributesEnum.Attribute3.index();
    public static final int ATTRIBUTE4 = AttributesEnum.Attribute4.index();
    public static final int ATTRIBUTE5 = AttributesEnum.Attribute5.index();
    public static final int ATTRIBUTE6 = AttributesEnum.Attribute6.index();
    public static final int ATTRIBUTE7 = AttributesEnum.Attribute7.index();
    public static final int ATTRIBUTE8 = AttributesEnum.Attribute8.index();
    public static final int ATTRIBUTE9 = AttributesEnum.Attribute9.index();
    public static final int ATTRIBUTE10 = AttributesEnum.Attribute10.index();
    public static final int ATTRIBUTE11 = AttributesEnum.Attribute11.index();
    public static final int ATTRIBUTE12 = AttributesEnum.Attribute12.index();
    public static final int ATTRIBUTE13 = AttributesEnum.Attribute13.index();
    public static final int ATTRIBUTE14 = AttributesEnum.Attribute14.index();
    public static final int ATTRIBUTE15 = AttributesEnum.Attribute15.index();
    public static final int FECCREATIONDATE = AttributesEnum.FecCreationDate.index();
    public static final int NUMCREATEDBY = AttributesEnum.NumCreatedBy.index();
    public static final int FECLASTUPDATEDATE = AttributesEnum.FecLastUpdateDate.index();
    public static final int NUMLASTUPDATEDBY = AttributesEnum.NumLastUpdatedBy.index();
    public static final int NUMLASTUPDATELOGIN = AttributesEnum.NumLastUpdateLogin.index();

    /**
     * This is the default constructor (do not remove).
     */
    public LmkIntMappingCatTabViewRowImpl() {
    }

    /**
     * Gets LmkIntMappingCatTab entity object.
     * @return the LmkIntMappingCatTab
     */
    public EntityImpl getLmkIntMappingCatTab() {
        return (EntityImpl) getEntity(ENTITY_LMKINTMAPPINGCATTAB);
    }

    /**
     * Gets the attribute value for ID_MAPPING using the alias name IdMapping.
     * @return the ID_MAPPING
     */
    public Integer getIdMapping() {
        return (Integer) getAttributeInternal(IDMAPPING);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_MAPPING using the alias name IdMapping.
     * @param value value to set the ID_MAPPING
     */
    public void setIdMapping(Integer value) {
        setAttributeInternal(IDMAPPING, value);
    }

    /**
     * Gets the attribute value for NOM_RELATION using the alias name NomRelation.
     * @return the NOM_RELATION
     */
    public String getNomRelation() {
        return (String) getAttributeInternal(NOMRELATION);
    }

    /**
     * Sets <code>value</code> as attribute value for NOM_RELATION using the alias name NomRelation.
     * @param value value to set the NOM_RELATION
     */
    public void setNomRelation(String value) {
        setAttributeInternal(NOMRELATION, value);
    }

    /**
     * Gets the attribute value for NOM_ORIGIN using the alias name NomOrigin.
     * @return the NOM_ORIGIN
     */
    public String getNomOrigin() {
        return (String) getAttributeInternal(NOMORIGIN);
    }

    /**
     * Sets <code>value</code> as attribute value for NOM_ORIGIN using the alias name NomOrigin.
     * @param value value to set the NOM_ORIGIN
     */
    public void setNomOrigin(String value) {
        setAttributeInternal(NOMORIGIN, value);
    }

    /**
     * Gets the attribute value for NOM_DESTINY using the alias name NomDestiny.
     * @return the NOM_DESTINY
     */
    public String getNomDestiny() {
        return (String) getAttributeInternal(NOMDESTINY);
    }

    /**
     * Sets <code>value</code> as attribute value for NOM_DESTINY using the alias name NomDestiny.
     * @param value value to set the NOM_DESTINY
     */
    public void setNomDestiny(String value) {
        setAttributeInternal(NOMDESTINY, value);
    }

    /**
     * Gets the attribute value for VAL_VALUE_RELATION using the alias name ValValueRelation.
     * @return the VAL_VALUE_RELATION
     */
    public String getValValueRelation() {
        return (String) getAttributeInternal(VALVALUERELATION);
    }

    /**
     * Sets <code>value</code> as attribute value for VAL_VALUE_RELATION using the alias name ValValueRelation.
     * @param value value to set the VAL_VALUE_RELATION
     */
    public void setValValueRelation(String value) {
        setAttributeInternal(VALVALUERELATION, value);
    }

    /**
     * Gets the attribute value for VAL_VALUE_ORIGIN using the alias name ValValueOrigin.
     * @return the VAL_VALUE_ORIGIN
     */
    public String getValValueOrigin() {
        return (String) getAttributeInternal(VALVALUEORIGIN);
    }

    /**
     * Sets <code>value</code> as attribute value for VAL_VALUE_ORIGIN using the alias name ValValueOrigin.
     * @param value value to set the VAL_VALUE_ORIGIN
     */
    public void setValValueOrigin(String value) {
        setAttributeInternal(VALVALUEORIGIN, value);
    }

    /**
     * Gets the attribute value for VAL_VALUE_DESTINY using the alias name ValValueDestiny.
     * @return the VAL_VALUE_DESTINY
     */
    public String getValValueDestiny() {
        return (String) getAttributeInternal(VALVALUEDESTINY);
    }

    /**
     * Sets <code>value</code> as attribute value for VAL_VALUE_DESTINY using the alias name ValValueDestiny.
     * @param value value to set the VAL_VALUE_DESTINY
     */
    public void setValValueDestiny(String value) {
        setAttributeInternal(VALVALUEDESTINY, value);
    }

    /**
     * Gets the attribute value for IND_SYS_SYSTEM using the alias name IndSysSystem.
     * @return the IND_SYS_SYSTEM
     */
    public String getIndSysSystem() {
        return (String) getAttributeInternal(INDSYSSYSTEM);
    }

    /**
     * Sets <code>value</code> as attribute value for IND_SYS_SYSTEM using the alias name IndSysSystem.
     * @param value value to set the IND_SYS_SYSTEM
     */
    public void setIndSysSystem(String value) {
        setAttributeInternal(INDSYSSYSTEM, value);
    }

    /**
     * Gets the attribute value for IND_SYS_ORIGIN using the alias name IndSysOrigin.
     * @return the IND_SYS_ORIGIN
     */
    public String getIndSysOrigin() {
        return (String) getAttributeInternal(INDSYSORIGIN);
    }

    /**
     * Sets <code>value</code> as attribute value for IND_SYS_ORIGIN using the alias name IndSysOrigin.
     * @param value value to set the IND_SYS_ORIGIN
     */
    public void setIndSysOrigin(String value) {
        setAttributeInternal(INDSYSORIGIN, value);
    }

    /**
     * Gets the attribute value for IND_SYS_DESTINY using the alias name IndSysDestiny.
     * @return the IND_SYS_DESTINY
     */
    public String getIndSysDestiny() {
        return (String) getAttributeInternal(INDSYSDESTINY);
    }

    /**
     * Sets <code>value</code> as attribute value for IND_SYS_DESTINY using the alias name IndSysDestiny.
     * @param value value to set the IND_SYS_DESTINY
     */
    public void setIndSysDestiny(String value) {
        setAttributeInternal(INDSYSDESTINY, value);
    }

    /**
     * Gets the attribute value for IND_USED_BY using the alias name IndUsedBy.
     * @return the IND_USED_BY
     */
    public String getIndUsedBy() {
        return (String) getAttributeInternal(INDUSEDBY);
    }

    /**
     * Sets <code>value</code> as attribute value for IND_USED_BY using the alias name IndUsedBy.
     * @param value value to set the IND_USED_BY
     */
    public void setIndUsedBy(String value) {
        setAttributeInternal(INDUSEDBY, value);
    }

    /**
     * Gets the attribute value for IND_DESCRIPTION using the alias name IndDescription.
     * @return the IND_DESCRIPTION
     */
    public String getIndDescription() {
        return (String) getAttributeInternal(INDDESCRIPTION);
    }

    /**
     * Sets <code>value</code> as attribute value for IND_DESCRIPTION using the alias name IndDescription.
     * @param value value to set the IND_DESCRIPTION
     */
    public void setIndDescription(String value) {
        setAttributeInternal(INDDESCRIPTION, value);
    }

    /**
     * Gets the attribute value for ID_MAPPING_REL using the alias name IdMappingRel.
     * @return the ID_MAPPING_REL
     */
    public Integer getIdMappingRel() {
        return (Integer) getAttributeInternal(IDMAPPINGREL);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_MAPPING_REL using the alias name IdMappingRel.
     * @param value value to set the ID_MAPPING_REL
     */
    public void setIdMappingRel(Integer value) {
        setAttributeInternal(IDMAPPINGREL, value);
    }

    /**
     * Gets the attribute value for IND_ESTATUS using the alias name IndEstatus.
     * @return the IND_ESTATUS
     */
    public String getIndEstatus() {
        return (String) getAttributeInternal(INDESTATUS);
    }

    /**
     * Sets <code>value</code> as attribute value for IND_ESTATUS using the alias name IndEstatus.
     * @param value value to set the IND_ESTATUS
     */
    public void setIndEstatus(String value) {
        setAttributeInternal(INDESTATUS, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE_CATEGORY using the alias name AttributeCategory.
     * @return the ATTRIBUTE_CATEGORY
     */
    public String getAttributeCategory() {
        return (String) getAttributeInternal(ATTRIBUTECATEGORY);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE_CATEGORY using the alias name AttributeCategory.
     * @param value value to set the ATTRIBUTE_CATEGORY
     */
    public void setAttributeCategory(String value) {
        setAttributeInternal(ATTRIBUTECATEGORY, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE1 using the alias name Attribute1.
     * @return the ATTRIBUTE1
     */
    public String getAttribute1() {
        return (String) getAttributeInternal(ATTRIBUTE1);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE1 using the alias name Attribute1.
     * @param value value to set the ATTRIBUTE1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE2 using the alias name Attribute2.
     * @return the ATTRIBUTE2
     */
    public String getAttribute2() {
        return (String) getAttributeInternal(ATTRIBUTE2);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE2 using the alias name Attribute2.
     * @param value value to set the ATTRIBUTE2
     */
    public void setAttribute2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE3 using the alias name Attribute3.
     * @return the ATTRIBUTE3
     */
    public String getAttribute3() {
        return (String) getAttributeInternal(ATTRIBUTE3);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE3 using the alias name Attribute3.
     * @param value value to set the ATTRIBUTE3
     */
    public void setAttribute3(String value) {
        setAttributeInternal(ATTRIBUTE3, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE4 using the alias name Attribute4.
     * @return the ATTRIBUTE4
     */
    public String getAttribute4() {
        return (String) getAttributeInternal(ATTRIBUTE4);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE4 using the alias name Attribute4.
     * @param value value to set the ATTRIBUTE4
     */
    public void setAttribute4(String value) {
        setAttributeInternal(ATTRIBUTE4, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE5 using the alias name Attribute5.
     * @return the ATTRIBUTE5
     */
    public String getAttribute5() {
        return (String) getAttributeInternal(ATTRIBUTE5);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE5 using the alias name Attribute5.
     * @param value value to set the ATTRIBUTE5
     */
    public void setAttribute5(String value) {
        setAttributeInternal(ATTRIBUTE5, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE6 using the alias name Attribute6.
     * @return the ATTRIBUTE6
     */
    public String getAttribute6() {
        return (String) getAttributeInternal(ATTRIBUTE6);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE6 using the alias name Attribute6.
     * @param value value to set the ATTRIBUTE6
     */
    public void setAttribute6(String value) {
        setAttributeInternal(ATTRIBUTE6, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE7 using the alias name Attribute7.
     * @return the ATTRIBUTE7
     */
    public String getAttribute7() {
        return (String) getAttributeInternal(ATTRIBUTE7);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE7 using the alias name Attribute7.
     * @param value value to set the ATTRIBUTE7
     */
    public void setAttribute7(String value) {
        setAttributeInternal(ATTRIBUTE7, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE8 using the alias name Attribute8.
     * @return the ATTRIBUTE8
     */
    public String getAttribute8() {
        return (String) getAttributeInternal(ATTRIBUTE8);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE8 using the alias name Attribute8.
     * @param value value to set the ATTRIBUTE8
     */
    public void setAttribute8(String value) {
        setAttributeInternal(ATTRIBUTE8, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE9 using the alias name Attribute9.
     * @return the ATTRIBUTE9
     */
    public String getAttribute9() {
        return (String) getAttributeInternal(ATTRIBUTE9);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE9 using the alias name Attribute9.
     * @param value value to set the ATTRIBUTE9
     */
    public void setAttribute9(String value) {
        setAttributeInternal(ATTRIBUTE9, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE10 using the alias name Attribute10.
     * @return the ATTRIBUTE10
     */
    public String getAttribute10() {
        return (String) getAttributeInternal(ATTRIBUTE10);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE10 using the alias name Attribute10.
     * @param value value to set the ATTRIBUTE10
     */
    public void setAttribute10(String value) {
        setAttributeInternal(ATTRIBUTE10, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE11 using the alias name Attribute11.
     * @return the ATTRIBUTE11
     */
    public String getAttribute11() {
        return (String) getAttributeInternal(ATTRIBUTE11);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE11 using the alias name Attribute11.
     * @param value value to set the ATTRIBUTE11
     */
    public void setAttribute11(String value) {
        setAttributeInternal(ATTRIBUTE11, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE12 using the alias name Attribute12.
     * @return the ATTRIBUTE12
     */
    public String getAttribute12() {
        return (String) getAttributeInternal(ATTRIBUTE12);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE12 using the alias name Attribute12.
     * @param value value to set the ATTRIBUTE12
     */
    public void setAttribute12(String value) {
        setAttributeInternal(ATTRIBUTE12, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE13 using the alias name Attribute13.
     * @return the ATTRIBUTE13
     */
    public String getAttribute13() {
        return (String) getAttributeInternal(ATTRIBUTE13);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE13 using the alias name Attribute13.
     * @param value value to set the ATTRIBUTE13
     */
    public void setAttribute13(String value) {
        setAttributeInternal(ATTRIBUTE13, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE14 using the alias name Attribute14.
     * @return the ATTRIBUTE14
     */
    public String getAttribute14() {
        return (String) getAttributeInternal(ATTRIBUTE14);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE14 using the alias name Attribute14.
     * @param value value to set the ATTRIBUTE14
     */
    public void setAttribute14(String value) {
        setAttributeInternal(ATTRIBUTE14, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE15 using the alias name Attribute15.
     * @return the ATTRIBUTE15
     */
    public String getAttribute15() {
        return (String) getAttributeInternal(ATTRIBUTE15);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE15 using the alias name Attribute15.
     * @param value value to set the ATTRIBUTE15
     */
    public void setAttribute15(String value) {
        setAttributeInternal(ATTRIBUTE15, value);
    }

    /**
     * Gets the attribute value for FEC_CREATION_DATE using the alias name FecCreationDate.
     * @return the FEC_CREATION_DATE
     */
    public Timestamp getFecCreationDate() {
        return (Timestamp) getAttributeInternal(FECCREATIONDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for FEC_CREATION_DATE using the alias name FecCreationDate.
     * @param value value to set the FEC_CREATION_DATE
     */
    public void setFecCreationDate(Timestamp value) {
        setAttributeInternal(FECCREATIONDATE, value);
    }

    /**
     * Gets the attribute value for NUM_CREATED_BY using the alias name NumCreatedBy.
     * @return the NUM_CREATED_BY
     */
    public Integer getNumCreatedBy() {
        return (Integer) getAttributeInternal(NUMCREATEDBY);
    }

    /**
     * Sets <code>value</code> as attribute value for NUM_CREATED_BY using the alias name NumCreatedBy.
     * @param value value to set the NUM_CREATED_BY
     */
    public void setNumCreatedBy(Integer value) {
        setAttributeInternal(NUMCREATEDBY, value);
    }

    /**
     * Gets the attribute value for FEC_LAST_UPDATE_DATE using the alias name FecLastUpdateDate.
     * @return the FEC_LAST_UPDATE_DATE
     */
    public Timestamp getFecLastUpdateDate() {
        return (Timestamp) getAttributeInternal(FECLASTUPDATEDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for FEC_LAST_UPDATE_DATE using the alias name FecLastUpdateDate.
     * @param value value to set the FEC_LAST_UPDATE_DATE
     */
    public void setFecLastUpdateDate(Timestamp value) {
        setAttributeInternal(FECLASTUPDATEDATE, value);
    }

    /**
     * Gets the attribute value for NUM_LAST_UPDATED_BY using the alias name NumLastUpdatedBy.
     * @return the NUM_LAST_UPDATED_BY
     */
    public Integer getNumLastUpdatedBy() {
        return (Integer) getAttributeInternal(NUMLASTUPDATEDBY);
    }

    /**
     * Sets <code>value</code> as attribute value for NUM_LAST_UPDATED_BY using the alias name NumLastUpdatedBy.
     * @param value value to set the NUM_LAST_UPDATED_BY
     */
    public void setNumLastUpdatedBy(Integer value) {
        setAttributeInternal(NUMLASTUPDATEDBY, value);
    }

    /**
     * Gets the attribute value for NUM_LAST_UPDATE_LOGIN using the alias name NumLastUpdateLogin.
     * @return the NUM_LAST_UPDATE_LOGIN
     */
    public Integer getNumLastUpdateLogin() {
        return (Integer) getAttributeInternal(NUMLASTUPDATELOGIN);
    }

    /**
     * Sets <code>value</code> as attribute value for NUM_LAST_UPDATE_LOGIN using the alias name NumLastUpdateLogin.
     * @param value value to set the NUM_LAST_UPDATE_LOGIN
     */
    public void setNumLastUpdateLogin(Integer value) {
        setAttributeInternal(NUMLASTUPDATELOGIN, value);
    }
}

