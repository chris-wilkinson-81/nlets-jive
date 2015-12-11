<html>
<head>
    <title><@s.text name="rstpwd.reset_yr_password.title" /></title>

    <meta name="nouserbar" content="true"/>

</head>
<body class="jive-body-formpage jive-body-formpage-validate jive-body-formpage-emailpassword">

    <!-- BEGIN layout -->
    <div class="j-layout j-layout-l clearfix">

        <!-- BEGIN large column -->
        <div class="j-column-wrap-l">
            <div class="j-column j-column-l">

            <#if passwordResetSupported>

                <!-- BEGIN reset password form block -->
                <div class="j-box jive-validate-formblock clearfix">
                    <div class="j-box-body j-box-body-padding j-rc4">
                    <#include "/template/global/include/form-message.ftl" />

                        <div id="jive-validate-formblock">

                        <h1><@s.text name="rstpwd.reset_yr_password.title" /></h1>

                        <#if success>
                            <p><@s.text name="rstpwd.email_sent.instruc" /></p>
                        <#else>
                            <p><@s.text name="rstpwd.howto.text" /></p>
                        </#if>

                        <#if !success>
                        <form action="<@s.url action='emailPasswordToken'/>" class="j-form" method="post" name="resetForm" autocomplete="off">

                            <#-- Username -->
                            <div id="jive-password-username" class="jive-label-input-pair j-form-row">
                                <label for="username"><@s.text name="global.username"/></label>
                                <input type="text" id="username" name="username" value="" size="30" maxlength="150">
                                <@macroFieldErrors name="username"/>
                                <#-- or -->
                                <p><@s.text name="global.or"/></p>
                            </div>

                            <#-- Email Address -->
                            <div id="jive-password-email" class="jive-label-input-pair j-form-row">
                                <label for="emailAddress"><@s.text name="global.email"/></label>
                                <input type="text" id="emailAddress" name="emailAddress" value="" size="30" maxlength="150">
                                <@macroFieldErrors name="emailAddress"/>
                            </div>

                            
                            <div class="j-form-row">
                                <input type="submit" name="saveButton" class="jive-form-button-submit j-btn-callout" value="<@s.text name='global.continue' />"/>
                                <input type="submit" name="method:cancel" class="jive-form-button-cancel" value="<@s.text name='global.cancel' />"/>
                            </div>

                        </form>
                        </#if>

                        </div>

                    </div>
                </div>
                <!-- END reset password form block -->


            <script type="text/javascript">
                $j(function(){
                    $j("#username").focus();
                    
                    //Begin NLETS customization
                	$j(window).resize(function(){
						var wH=$j(window).height();
						if(wH<700){
							$j('.jive-body-formpage-emailpassword #j-footer').css('position','relative');
							$j('.jive-body-formpage-emailpassword #j-footer #j-footer-poweredBy').css('margin-right','6.5%');
						}
						else{
							$j('.jive-body-formpage-emailpassword #j-footer').css('position','absolute');
							$j('.jive-body-formpage-emailpassword #j-footer #j-footer-poweredBy').css('margin-right','8.5%');
						}
					});
					if($j(window).height()<700){
						$j('.jive-body-formpage-emailpassword #j-footer').css('position','relative');
						$j('.jive-body-formpage-emailpassword #j-footer #j-footer-poweredBy').css('margin-right','6.5%');
					 }
					 else{
						$j('.jive-body-formpage-emailpassword #j-footer').css('position','absolute');
						$j('.jive-body-formpage-emailpassword #j-footer #j-footer-poweredBy').css('margin-right','8.5%');
						}
					});
                	//End NLETS customization
                });
            </script>

            <#else>
                <p><@s.text name="rstpwd.err.func_disabled.text" /></p>
            </#if>


            </div>
        </div>
        <!-- END large column -->

    </div>
    <!-- END layout -->


</body>
</html>
