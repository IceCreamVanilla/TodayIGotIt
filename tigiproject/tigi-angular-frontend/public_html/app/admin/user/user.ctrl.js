(function() {
    angular.module("app.admin.user")
        .controller("AdminUser", function($window, $scope, $rootScope, UserSvc) {
            if ($rootScope.curLogin == null || $rootScope.curLogin.userName === undefined || $rootScope.curLogin.userRoles[0].type != 'ADMIN') {
                $window.location.href = "#/home";
            }

            $scope.users = [];
            $scope.startAdminUserFrom = 0;

            $scope.$on('$viewContentLoaded', function() {
                UserSvc.findAllUser($cookieStore.get('curUser'), $cookieStore.get('curPass'))
                    .then(function(response) {
                        $scope.users = response;
                    }, function(err) {
                        console.log("Error: " + err);
                    });
            });
        })
        .directive("navPageLink", function() {
            return {
                restrict: "A",
                link: function(scope, elem, attrs) {
                    $(elem).on('click', function() {
                        // var aa = $(this).attr('id').substr(14);
                        // $(elem).scope().startAdminUserFrom = +aa;
                        // $(elem).scope().$apply();

                        // $('.startAdminUserFromIndex').removeClass('active');
                        // $('#startAdminUserFromIndex' + $(this).scope().startAdminUserFrom).addClass('active');

                        // console.log($(this));
                        // console.log(aa);

                        return false;
                    })
                }
            }
        });
})();